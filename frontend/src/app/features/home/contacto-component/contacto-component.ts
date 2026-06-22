import { Component, ChangeDetectorRef, CUSTOM_ELEMENTS_SCHEMA, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Subject, combineLatest, of } from 'rxjs';
import {
  debounceTime,
  distinctUntilChanged,
  switchMap,
  filter,
  takeUntil,
  catchError,
  timeout,
  finalize
} from 'rxjs/operators';
import { Especialidad } from '../../../model/Especialidad';
import 'iconify-icon';

export interface SlotDisponible {
  medicoId: number;
  medicoNombre: string;
  medicoApellidoPaterno: string;
  medicoGenero: string;
  especialidadNombre: string;
  consultorioId: number;
  horasDisponibles: string[];
}

export interface SlotSeleccionado {
  medicoId: number;
  medicoNombre: string;
  medicoApellidoPaterno: string;
  medicoGenero: string;
  especialidadNombre: string;
  consultorioId: number;
  hora: string;
}

@Component({
  selector: 'app-contacto',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './contacto-component.html',
  styleUrls: ['./contacto-component.css']
})
export class ContactoComponent implements OnInit, OnDestroy {

  private baseUrl = 'http://localhost:8080/api/lading';
  private apiUrl  = 'http://localhost:8080/api';

  // Sujeto para cancelar suscripciones al destruir el componente
  private destroy$ = new Subject<void>();

  pasoActual = 1;

  reservaForm!: FormGroup;
  contactoForm!: FormGroup;

  especialidades: Especialidad[] = [];
  slots: SlotDisponible[] = [];

  cargandoSlots = false;
  cargandoDni   = false;
  reservando    = false;
  reservaExitosa = false;
  errorReserva  = '';
  dniEncontrado = false;
  pacienteExistente = false;

  slotSeleccionado: SlotSeleccionado | null = null;

  gruposSanguineos = [
    'A_POSITIVO', 'A_NEGATIVO', 'B_POSITIVO', 'B_NEGATIVO',
    'AB_POSITIVO', 'AB_NEGATIVO', 'O_POSITIVO', 'O_NEGATIVO'
  ];

  constructor(private fb: FormBuilder, private http: HttpClient, private cd: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.initForms();
    this.cargarEspecialidades();
    this.suscribirCambiosSlots();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private initForms(): void {
    this.reservaForm = this.fb.group({
      especialidadId: ['', Validators.required],
      fecha: ['', Validators.required],
      motivoConsulta: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(200)]]
    });

    this.contactoForm = this.fb.group({
      dni:            ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
      nombre:         ['', Validators.required],
      apellidoPaterno:['', Validators.required],
      apellidoMaterno:['', Validators.required],
      telefono:       ['', [Validators.required, Validators.pattern(/^\d{9}$/)]],
      email:          ['', [Validators.required, Validators.email]],
      direccion:      ['', Validators.required],
      fechaNacimiento:['', Validators.required],
      genero:         ['', Validators.required],
      grupoSanguineo: ['', Validators.required]
    });
  }

  private cargarEspecialidades(): void {
    this.http.get<Especialidad[]>(`${this.baseUrl}/especialidades`)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => this.especialidades = data,
        error: (err) => console.error('Error cargando especialidades', err)
      });
  }

  // ── PASO 1: suscripción reactiva a cambios de especialidad + fecha ─────
  private suscribirCambiosSlots(): void {
    const especialidadId$ = this.reservaForm.get('especialidadId')!.valueChanges;
    const fecha$          = this.reservaForm.get('fecha')!.valueChanges;

    combineLatest([especialidadId$, fecha$])
      .pipe(
        debounceTime(100),                          // espera 100ms tras el último cambio
        distinctUntilChanged(
          ([prevEsp, prevFecha], [currEsp, currFecha]) =>
            prevEsp === currEsp && prevFecha === currFecha
        ),
        filter(([especialidadId, fecha]) => !!especialidadId && !!fecha),
        switchMap(([especialidadId, fecha]) => {
          this.cargandoSlots = true;
          this.slots = [];
          this.slotSeleccionado = null;

          return this.http
            .get<SlotDisponible[]>(
              `${this.baseUrl}/slots?especialidadId=${especialidadId}&fecha=${fecha}`
            )
            .pipe(
              catchError(() => {
                this.cargandoSlots = false;
                return of([]);
              })
            );
        }),
        takeUntil(this.destroy$)
      )
      .subscribe({
        next: (data) => {
          this.slots = data;
          this.cargandoSlots = false;
        }
      });
  }

  // Mantener para compatibilidad con el template (cambio en select dispara valueChanges)
  onEspecialidadFechaChange(): void {
    // La lógica está en suscribirCambiosSlots() vía valueChanges.
    // Este método puede quedar vacío o usarse para un reset de UI inmediato.
    this.slotSeleccionado = null;
  }

  seleccionarSlot(slot: SlotDisponible, hora: string): void {
    this.slotSeleccionado = {
      medicoId: slot.medicoId,
      medicoNombre: slot.medicoNombre,
      medicoApellidoPaterno: slot.medicoApellidoPaterno,
      medicoGenero: slot.medicoGenero,
      especialidadNombre: slot.especialidadNombre,
      consultorioId: slot.consultorioId,
      hora
    };
  }

  isSlotSelected(slot: SlotDisponible, hora: string): boolean {
    return this.slotSeleccionado?.medicoId === slot.medicoId &&
           this.slotSeleccionado?.hora === hora;
  }

  // ── PASO 2: buscar paciente ──────────────────────────────────────────
  buscarPorDni(): void {
    const dni = this.contactoForm.get('dni')?.value;
    if (!dni || dni.length !== 8) return;

    this.cargandoDni = true;
    this.dniEncontrado = false;
    this.pacienteExistente = false;

    this.http.get<any>(`${this.baseUrl}/consulta-dni/${dni}`)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => {
          if (data) {
            this.dniEncontrado = true;
            this.pacienteExistente = data.existeLocal;
            this.contactoForm.patchValue({
              nombre:          data.nombre || '',
              apellidoPaterno: data.apellidoPaterno || '',
              apellidoMaterno: data.apellidoMaterno || '',
              telefono:        data.telefono || '',
              email:           data.email || '',
              direccion:       data.direccion || '',
              genero:          data.genero || '',
              grupoSanguineo:  data.grupoSanguineo || '',
              fechaNacimiento: data.fechaNacimiento || ''
            });
            if (data.existeLocal) {
              this.bloquearCamposPaciente();
            }
          }
          this.cargandoDni = false;
        },
        error: () => { this.cargandoDni = false; }
      });
  }

  private bloquearCamposPaciente(): void {
    ['nombre', 'apellidoPaterno', 'apellidoMaterno', 'fechaNacimiento', 'genero', 'grupoSanguineo']
      .forEach(campo => this.contactoForm.get(campo)?.disable());
  }

  private habilitarCamposPaciente(): void {
    Object.keys(this.contactoForm.controls).forEach(campo => {
      if (campo !== 'dni') this.contactoForm.get(campo)?.enable();
    });
  }

  // ── PASO 3: confirmar reserva ────────────────────────────────────────
  confirmarReserva(): void {
    if (!this.slotSeleccionado) return;
    const formValue = this.contactoForm.getRawValue();

    this.reservando = true;
    this.errorReserva = '';

    const payload = {
      dni:             formValue.dni,
      nombre:          formValue.nombre,
      apellidoPaterno: formValue.apellidoPaterno,
      apellidoMaterno: formValue.apellidoMaterno,
      telefono:        formValue.telefono,
      email:           formValue.email,
      direccion:       formValue.direccion,
      fechaNacimiento: formValue.fechaNacimiento,
      genero:          formValue.genero,
      grupoSanguineo:  formValue.grupoSanguineo,
      medicoId:        this.slotSeleccionado.medicoId,
      consultorioId:   this.slotSeleccionado.consultorioId,
      fechaHora:       this.slotSeleccionado.hora,
      motivoConsulta:  this.reservaForm.value.motivoConsulta
    };

    this.http.post(this.baseUrl + '/reserva', payload, { responseType: 'text' })
      .pipe(
        takeUntil(this.destroy$),
        timeout(60000),
        finalize(() => { this.reservando = false; })
      )
      .subscribe({
        next: () => {
          this.reservaExitosa = true;
          this.pasoActual = 3;
          this.cd.detectChanges();
        },
        error: (err) => {
          this.errorReserva = err?.error || 'Error al procesar la reserva. Intente nuevamente.';
        }
      });
  }

  // ── Helpers UI ───────────────────────────────────────────────────────
  getNombreDoctor(slot: SlotDisponible): string {
    const titulo = slot.medicoGenero === 'FEMENINO' ? 'Dra.' : 'Dr.';
    return `${titulo} ${slot.medicoNombre} ${slot.medicoApellidoPaterno}`;
  }

  formatearHora(fechaHoraStr: string): string {
    const partes = fechaHoraStr.split('T');
    if (partes.length < 2) return fechaHoraStr;
    const [h, m] = partes[1].substring(0, 5).split(':').map(Number);
    const ampm = h >= 12 ? 'PM' : 'AM';
    const h12  = h % 12 || 12;
    return `${h12}:${m.toString().padStart(2, '0')} ${ampm}`;
  }

  formatearDia(fechaStr: string): string {
    if (!fechaStr) return '';
    const [anio, mes, dia] = fechaStr.split('-').map(Number);
    return new Date(anio, mes - 1, dia)
      .toLocaleDateString('es-PE', { weekday: 'long', day: 'numeric', month: 'long' });
  }

  get fechaMin(): string {
    const hoy = new Date();
    hoy.setDate(hoy.getDate() + 1);
    return hoy.toISOString().split('T')[0];
  }

  get fechaMax(): string {
    const limite = new Date();
    limite.setMonth(limite.getMonth() + 3);
    return limite.toISOString().split('T')[0];
  }

  siguientePaso(): void {
    if (this.pasoActual === 1 && (!this.reservaForm.valid || !this.slotSeleccionado)) return;
    this.pasoActual++;
    if (this.pasoActual === 2) {
      this.habilitarCamposPaciente();
      this.dniEncontrado = false;
      this.pacienteExistente = false;
      this.contactoForm.reset();
    }
  }

  anteriorPaso(): void {
    if (this.pasoActual > 1) {
      this.pasoActual--;
      if (this.pasoActual === 1) {
        this.habilitarCamposPaciente();
        this.dniEncontrado = false;
        this.pacienteExistente = false;
      }
    }
  }

  nuevaReserva(): void {
    this.pasoActual = 1;
    this.reservaExitosa = false;
    this.reservaForm.reset();
    this.contactoForm.reset();
    this.habilitarCamposPaciente();
    this.slotSeleccionado = null;
    this.slots = [];
    this.dniEncontrado = false;
    this.pacienteExistente = false;
    this.errorReserva = '';
  }
}
