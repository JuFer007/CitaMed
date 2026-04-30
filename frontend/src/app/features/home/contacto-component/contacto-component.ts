import { Component, CUSTOM_ELEMENTS_SCHEMA, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Especialidad } from '../../../model/Especialidad';
import { HorarioMedico } from '../../../model/HorarioMedico ';
import { Medico } from '../../../model/Medico ';
import { ReniecData } from '../../../model/ReniecData ';
import { SlotHorario } from '../../../model/SlotHorario ';
import 'iconify-icon';

@Component({
  selector: 'app-contacto',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './contacto-component.html',
  styleUrls: ['./contacto-component.css']
})
export class ContactoComponent implements OnInit {
 
  private baseUrl = 'http://localhost:8080/api';
 
  pasoActual = 1;
 
  reservaForm!: FormGroup;
  contactoForm!: FormGroup;
 
  especialidades: Especialidad[] = [];
  medicosDisponibles: Medico[] = [];
  slotsDisponibles: SlotHorario[] = [];
 
  cargandoDni = false;
  cargandoMedicos = false;
  reservando = false;
  reservaExitosa = false;
  errorReserva = '';
  dniEncontrado = false;
 
  medicoSeleccionado: Medico | null = null;
  slotSeleccionado: SlotHorario | null = null;
 
  pacienteExistente: any = null;
 
  diasSemana: { [key: string]: number } = {
    'DOMINGO': 0, 'LUNES': 1, 'MARTES': 2, 'MIERCOLES': 3,
    'JUEVES': 4, 'VIERNES': 5, 'SABADO': 6
  };
 
  gruposSanguineos = [
    'A_POSITIVO', 'A_NEGATIVO', 'B_POSITIVO', 'B_NEGATIVO',
    'AB_POSITIVO', 'AB_NEGATIVO', 'O_POSITIVO', 'O_NEGATIVO'
  ];
 
  constructor(private fb: FormBuilder, private http: HttpClient) { }
 
  ngOnInit(): void {
    this.initForms();
    this.cargarEspecialidades();
  }
 
  private initForms(): void {
    this.reservaForm = this.fb.group({
      especialidadId: ['', Validators.required],
      fecha: ['', Validators.required],
      motivoConsulta: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(200)]]
    });
 
    this.contactoForm = this.fb.group({
      dni: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
      nombre: ['', Validators.required],
      apellidoPaterno: ['', Validators.required],
      apellidoMaterno: ['', Validators.required],
      telefono: ['', [Validators.required, Validators.pattern(/^\d{9}$/)]],
      email: ['', [Validators.required, Validators.email]],
      direccion: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      genero: ['', Validators.required],
      grupoSanguineo: ['', Validators.required]
    });
  }
 
  private cargarEspecialidades(): void {
    this.http.get<Especialidad[]>(`${this.baseUrl}/especialidad`).subscribe({
      next: (data) => this.especialidades = data,
      error: (err) => console.error('Error cargando especialidades', err)
    });
  }
 
  onEspecialidadFechaChange(): void {
    const especialidadId = this.reservaForm.get('especialidadId')?.value;
    const fecha = this.reservaForm.get('fecha')?.value;
 
    if (!especialidadId || !fecha) return;
 
    this.cargandoMedicos = true;
    this.medicosDisponibles = [];
    this.slotsDisponibles = [];
    this.medicoSeleccionado = null;
    this.slotSeleccionado = null;
 
    this.http.get<Medico[]>(`${this.baseUrl}/medico/especialidad/${especialidadId}`).subscribe({
      next: (medicos) => {
        this.medicosDisponibles = medicos;
        this.calcularSlots(medicos, fecha);
        this.cargandoMedicos = false;
      },
      error: () => { this.cargandoMedicos = false; }
    });
  }
 
  private calcularSlots(medicos: Medico[], fechaStr: string): void {
    const fecha = new Date(fechaStr + 'T12:00:00');
    const diaSemana = fecha.getDay();
    const nombreDia = Object.keys(this.diasSemana).find(k => this.diasSemana[k] === diaSemana) || '';
 
    this.slotsDisponibles = [];
 
    medicos.forEach(medico => {
      this.http.get<HorarioMedico[]>(`${this.baseUrl}/horarioMedico/medico/${medico.id}`).subscribe({
        next: (horarios) => {
          horarios.forEach(horario => {
            if (horario.dia === nombreDia && horario.activo) {
              const slots = this.generarSlotsDeTiempo(horario.horaInicio, horario.horaFin, fechaStr);
              slots.forEach(slot => {
                this.slotsDisponibles.push({ medico, horario, horaDisponible: slot });
              });
            }
          });
        }
      });
    });
  }
 
  private generarSlotsDeTiempo(inicio: string, fin: string, fecha: string): string[] {
    const slots: string[] = [];
    const [hInicio, mInicio] = inicio.split(':').map(Number);
    const [hFin, mFin] = fin.split(':').map(Number);
 
    let current = hInicio * 60 + mInicio;
    const end = hFin * 60 + mFin;
 
    while (current + 30 <= end) {
      const h = Math.floor(current / 60).toString().padStart(2, '0');
      const m = (current % 60).toString().padStart(2, '0');
      slots.push(`${fecha}T${h}:${m}:00`);
      current += 30;
    }
 
    return slots;
  }
 
  seleccionarSlot(slot: SlotHorario): void {
    this.slotSeleccionado = slot;
    this.medicoSeleccionado = slot.medico;
  }
 
  buscarPorDni(): void {
    const dni = this.contactoForm.get('dni')?.value;
    if (!dni || dni.length !== 8) return;
 
    this.cargandoDni = true;
    this.dniEncontrado = false;
 
    this.http.get<any>(`${this.baseUrl}/paciente/dni/${dni}`).subscribe({
      next: (paciente) => {
        if (paciente) {
          this.pacienteExistente = paciente;
          this.dniEncontrado = true;
          this.contactoForm.patchValue({
            nombre: paciente.nombre,
            apellidoPaterno: paciente.apellidoPaterno,
            apellidoMaterno: paciente.apellidoMaterno,
            telefono: paciente.telefono,
            email: paciente.email,
            direccion: paciente.direccion,
            genero: paciente.genero,
            grupoSanguineo: paciente.grupoSanguineo
          });
          if (paciente.fechaNacimiento) {
            this.contactoForm.patchValue({ fechaNacimiento: paciente.fechaNacimiento });
          }
        }
        this.cargandoDni = false;
      },
      error: () => {
        this.consultarReniec(dni);
      }
    });
  }
 
  private consultarReniec(dni: string): void {
    this.http.get<ReniecData>(`${this.baseUrl}/reniec/dni/${dni}`).subscribe({
      next: (data) => {
        if (data) {
          this.dniEncontrado = true;
          this.contactoForm.patchValue({
            nombre: data.first_name || '',
            apellidoPaterno: data.first_last_name || '',
            apellidoMaterno: data.second_last_name || ''
          });
        }
        this.cargandoDni = false;
      },
      error: () => {
        this.cargandoDni = false;
      }
    });
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
 
  formatearHora(fechaHoraStr: string): string {
    const partes = fechaHoraStr.split('T');
    if (partes.length < 2) return fechaHoraStr;
    const horaParte = partes[1].substring(0, 5);
    const [h, m] = horaParte.split(':').map(Number);
    const ampm = h >= 12 ? 'PM' : 'AM';
    const h12 = h % 12 || 12;
    return `${h12}:${m.toString().padStart(2, '0')} ${ampm}`;
  }
 
  formatearDia(fechaStr: string): string {
    const fecha = new Date(fechaStr + 'T12:00:00');
    return fecha.toLocaleDateString('es-PE', { weekday: 'long', day: 'numeric', month: 'long' });
  }
 
  getNombreDoctor(medico: Medico): string {
    const titulo = medico.genero === 'FEMENINO' ? 'Dra.' : 'Dr.';
    return `${titulo} ${medico.nombre} ${medico.apellidoPaterno}`;
  }
 
  siguientePaso(): void {
    if (this.pasoActual === 1 && (!this.reservaForm.valid || !this.slotSeleccionado)) return;
    this.pasoActual++;
  }
 
  anteriorPaso(): void {
    if (this.pasoActual > 1) this.pasoActual--;
  }
 
  async confirmarReserva(): Promise<void> {
    if (this.contactoForm.invalid || !this.slotSeleccionado) return;
 
    this.reservando = true;
    this.errorReserva = '';
 
    try {
      let pacienteId: number;
 
      if (this.pacienteExistente) {
        pacienteId = this.pacienteExistente.id;
      } else {
        // Registrar paciente nuevo
        const pacienteData = {
          ...this.contactoForm.value,
          nombre: this.contactoForm.value.nombre.toUpperCase(),
          apellidoPaterno: this.contactoForm.value.apellidoPaterno.toUpperCase(),
          apellidoMaterno: this.contactoForm.value.apellidoMaterno.toUpperCase()
        };
 
        const pacienteResp = await this.http.post<any>(
          `${this.baseUrl}/paciente`, pacienteData
        ).toPromise();
 
        // Buscar el paciente recién creado
        const pacienteBuscado = await this.http.get<any>(
          `${this.baseUrl}/paciente/dni/${this.contactoForm.value.dni}`
        ).toPromise();
 
        pacienteId = pacienteBuscado.id;
      }
 
      // Crear la cita
      const citaData = {
        pacienteId: pacienteId,
        medicoId: this.slotSeleccionado.medico.id,
        consultorioId: this.slotSeleccionado.horario.id,
        fechaHora: this.slotSeleccionado.horaDisponible,
        motivoConsulta: this.reservaForm.value.motivoConsulta
      };
 
      await this.http.post(`${this.baseUrl}/cita`, citaData).toPromise();
 
      // Buscar la cita creada para el pago
      const citas = await this.http.get<any[]>(
        `${this.baseUrl}/cita/paciente/${pacienteId}`
      ).toPromise();
 
      if (citas && citas.length > 0) {
        const ultimaCita = citas[citas.length - 1];
        // Registrar pago
        const pagoData = {
          citaId: ultimaCita.id,
          monto: 80.00,
          metodoPago: 'EFECTIVO'
        };
        await this.http.post(`${this.baseUrl}/pago`, pagoData).toPromise();
      }
 
      await this.enviarCorreoConfirmacion();
 
      this.reservaExitosa = true;
      this.pasoActual = 3;
 
    } catch (error: any) {
      this.errorReserva = error?.error || 'Ocurrió un error al procesar la reserva. Por favor intente nuevamente.';
    } finally {
      this.reservando = false;
    }
  }
 
  private async enviarCorreoConfirmacion(): Promise<void> {
    if (!this.slotSeleccionado) return;
    const slot = this.slotSeleccionado;
    const nombre = this.contactoForm.value.nombre;
    const email = this.contactoForm.value.email;
    const doctor = this.getNombreDoctor(slot.medico);
    const fecha = this.formatearDia(this.reservaForm.value.fecha);
    const hora = this.formatearHora(slot.horaDisponible);
    const especialidad = slot.medico.especialidad?.nombre || '';
    const motivo = this.reservaForm.value.motivoConsulta;
 
    const mensajeContacto = {
      nombre: nombre,
      email: email,
      mensaje: `cita confirmada con ${doctor} - ${especialidad} el ${fecha} a las ${hora}. Motivo: ${motivo}`
    };
 
    try {
      await this.http.post(`${this.baseUrl}/contacto`, mensajeContacto).toPromise();
    } catch (e) {
      console.warn('No se pudo enviar correo de confirmación', e);
    }
  }
 
  nuevaReserva(): void {
    this.pasoActual = 1;
    this.reservaExitosa = false;
    this.reservaForm.reset();
    this.contactoForm.reset();
    this.slotSeleccionado = null;
    this.medicoSeleccionado = null;
    this.pacienteExistente = null;
    this.slotsDisponibles = [];
    this.dniEncontrado = false;
    this.errorReserva = '';
  }
 
  getEspecialidadNombre(id: string): string {
    return this.especialidades.find(e => e.id === +id)?.nombre || '';
  }
 
  agruparSlotsPorMedico(): { medico: Medico, slots: SlotHorario[] }[] {
    const mapa = new Map<number, { medico: Medico, slots: SlotHorario[] }>();
    this.slotsDisponibles.forEach(slot => {
      if (!mapa.has(slot.medico.id)) {
        mapa.set(slot.medico.id, { medico: slot.medico, slots: [] });
      }
      mapa.get(slot.medico.id)!.slots.push(slot);
    });
    return Array.from(mapa.values());
  }
}
