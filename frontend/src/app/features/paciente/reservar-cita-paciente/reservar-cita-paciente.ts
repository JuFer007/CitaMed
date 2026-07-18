import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PacientePortalService } from '../../../core/services/paciente-portal-service';
import { GlobalToast } from '../../../core/services/global-toast';

interface ConfirmacionData {
  especialidad: string;
  medico: string;
  fechaHora: string;
  precio: number;
  motivo: string;
  consultorio: string;
}

interface Slot {
  medicoId: number;
  medicoNombre: string;
  medicoApellidoPaterno: string;
  especialidadNombre: string;
  consultorioId: number;
  horasDisponibles: string[];
}

@Component({
  selector: 'app-reservar-cita-paciente',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './reservar-cita-paciente.html',
  styleUrl: './reservar-cita-paciente.css',
})
export class ReservarCitaPacienteComponent implements OnInit {
  paso = 1;

  especialidades: any[] = [];
  slots: Slot[] = [];

  especialidadSeleccionada: number | null = null;
  slotSeleccionado: string | null = null;
  consultorioId: number | null = null;
  medicoId: number | null = null;
  medicoNombre = '';
  medicoApellido = '';
  precio = 0;
  fechaSeleccionada = '';
  motivoConsulta = '';
  minDate = '';
  reservando = false;
  fechaSinHorarios = false;
  confirmacion?: ConfirmacionData;

  constructor(
    private portalService: PacientePortalService,
    private toast: GlobalToast,
    private cdr: ChangeDetectorRef,
  ) {
    const hoy = new Date();
    this.minDate = hoy.toISOString().split('T')[0];
    this.fechaSeleccionada = this.minDate;
  }

  ngOnInit(): void {
    this.portalService.obtenerEspecialidades().subscribe({
      next: (data) => {
        this.especialidades = data;
        this.cdr.markForCheck();
      },
      error: () => this.toast.error('Error al cargar especialidades'),
    });
  }

  seleccionarEspecialidad(id: number): void {
    this.especialidadSeleccionada = id;
    this.slots = [];
    this.slotSeleccionado = null;
    this.medicoId = null;
    this.precio = this.especialidades.find(e => e.id === id)?.precio || 0;
    this.paso = 2;
  }

  get consultorio(): string {
    if (!this.slots.length || !this.medicoId) return '';
    const s = this.slots.find(x => x.medicoId === this.medicoId);
    return s ? `Consultorio ${s.consultorioId}` : '';
  }

  cargarSlots(): void {
    if (!this.especialidadSeleccionada || !this.fechaSeleccionada) return;
    this.portalService.obtenerSlots(this.especialidadSeleccionada, this.fechaSeleccionada).subscribe({
      next: (data) => {
        this.slots = data;
        this.fechaSinHorarios = this.slots.length === 0;
        if (this.slots.length > 0) this.paso = 3;
        this.cdr.detectChanges();
      },
      error: () => this.toast.error('Error al cargar horarios'),
    });
  }

  cambiarFecha(): void {
    this.slots = [];
    this.slotSeleccionado = null;
    this.medicoId = null;
    this.fechaSinHorarios = false;
    this.cargarSlots();
  }

  seleccionarSlot(hora: string, medicoId: number, consultorioId: number, nombre: string, apellido: string): void {
    this.slotSeleccionado = hora;
    this.medicoId = medicoId;
    this.consultorioId = consultorioId;
    this.medicoNombre = nombre;
    this.medicoApellido = apellido;
    this.paso = 4;
  }

  confirmarReserva(): void {
    if (!this.especialidadSeleccionada || !this.medicoId || !this.slotSeleccionado || !this.consultorioId || !this.motivoConsulta.trim()) {
      this.toast.warn('Completa todos los campos');
      return;
    }

    this.reservando = true;
    this.portalService.reservarCita({
      especialidadId: this.especialidadSeleccionada,
      medicoId: this.medicoId,
      consultorioId: this.consultorioId,
      fechaHora: this.slotSeleccionado,
      motivoConsulta: this.motivoConsulta.trim(),
    }).subscribe({
      next: () => {
        this.reservando = false;
        this.confirmacion = {
          especialidad: this.especialidades.find(e => e.id === this.especialidadSeleccionada)?.nombre || '',
          medico: `Dr. ${this.medicoNombre} ${this.medicoApellido}`,
          fechaHora: this.slotSeleccionado ?? '',
          precio: this.precio,
          motivo: this.motivoConsulta.trim(),
          consultorio: this.consultorio,
        };
        this.paso = 5;
        this.portalService.notificarRecargarCitas();
      },
      error: (err) => {
        this.reservando = false;
        this.toast.error(err.error?.error || 'Error al reservar la cita');
      },
    });
  }

  reiniciar(): void {
    this.paso = 1;
    this.especialidadSeleccionada = null;
    this.medicoId = null;
    this.slotSeleccionado = null;
    this.consultorioId = null;
    this.medicoNombre = '';
    this.medicoApellido = '';
    this.motivoConsulta = '';
    this.slots = [];
    this.confirmacion = undefined;
  }

  formatearHora(fechaHora: string): string {
    try { return new Date(fechaHora).toLocaleTimeString('es-PE', { hour: '2-digit', minute: '2-digit' }); }
    catch { return fechaHora; }
  }

  formatearFecha(fechaHora: string): string {
    try { return new Date(fechaHora).toLocaleDateString('es-PE', { day: 'numeric', month: 'long', year: 'numeric' }); }
    catch { return fechaHora; }
  }
}
