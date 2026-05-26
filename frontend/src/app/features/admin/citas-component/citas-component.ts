import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { DatePickerModule } from 'primeng/datepicker';
import { SelectModule } from 'primeng/select';
import { TextareaModule } from 'primeng/textarea';
import { InputTextModule } from 'primeng/inputtext';
import { CitaService } from '../../../core/services/cita-service';
import { Cita, CitaDTO, EstadoCita } from '../../../model/Cita';
import { Medico } from '../../../model/Medico';
import { Paciente } from '../../../model/Paciente';
import { Consultorio } from '../../../model/Consultorio';
import { GlobalToast } from '../../../core/services/global-toast';

interface FiltroEstado {
  label: string;
  value: EstadoCita | null;
}

@Component({
  selector: 'app-citas-component',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    DialogModule,
    ButtonModule,
    TableModule,
    DatePickerModule,
    SelectModule,
    InputTextModule,
    TextareaModule,
  ],
  templateUrl: './citas-component.html',
  styleUrl: './citas-component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CitasComponent implements OnInit {
  citas: Cita[] = [];
  citasFiltradas: Cita[] = [];
  medicos: Medico[] = [];
  pacientes: Paciente[] = [];
  consultorios: Consultorio[] = [];

  // Fecha mínima para los datepickers (hoy)
  readonly hoy: Date = new Date();

  // Modales
  mostrarModal = false;
  mostrarDetalleModal = false;
  mostrarReprogramarModal = false;
  modoEdicion = false;

  // Formulario
  nuevaCita: CitaDTO = {
    pacienteId: 0,
    medicoId: 0,
    consultorioId: 0,
    fechaHora: '',
    motivoConsulta: ''
  };

  citaSeleccionada: Cita | null = null;
  nuevaFechaReprogramacion: Date | null = null;

  // Filtros
  terminoBusqueda = '';
  filtroEstado: EstadoCita | null = null;
  filtroMedico: number | null = null;
  filtroFechaInicio: Date | null = null;
  filtroFechaFin: Date | null = null;

  // Fecha para formulario
  fechaHoraCita: Date | null = null;

  estadosDisponibles: FiltroEstado[] = [
    { label: 'Todas', value: null },
    { label: 'Programada', value: EstadoCita.PROGRAMADA },
    { label: 'Atendida', value: EstadoCita.ATENDIDA },
    { label: 'Cancelada', value: EstadoCita.CANCELADA },
    { label: 'No Asistió', value: EstadoCita.NO_ASISTIO }
  ];

  constructor(
    private citaService: CitaService,
    private toast: GlobalToast,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos(): void {
    this.cargarCitas();
    this.cargarMedicos();
    this.cargarPacientes();
    this.cargarConsultorios();
  }

  cargarCitas(): void {
    this.citaService.obtenerTodas().subscribe({
      next: (data) => {
        this.citas = data.sort((a, b) =>
          new Date(b.fechaHora).getTime() - new Date(a.fechaHora).getTime()
        );
        this.aplicarFiltros();
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error(err);
        this.toast.error('No se pudieron cargar las citas');
      }
    });
  }

  cargarMedicos(): void {
    this.citaService.obtenerMedicos().subscribe({
      next: (data) => {
        this.medicos = data.filter(m => m.activo);
        this.cdr.markForCheck();
      },
      error: (err) => console.error(err)
    });
  }

  cargarPacientes(): void {
    this.citaService.obtenerPacientes().subscribe({
      next: (data) => {
        this.pacientes = data.filter(p => p.activo);
        this.cdr.markForCheck();
      },
      error: (err) => console.error(err)
    });
  }

  cargarConsultorios(): void {
    this.citaService.obtenerConsultoriosDisponibles().subscribe({
      next: (data) => {
        this.consultorios = data;
        this.cdr.markForCheck();
      },
      error: (err) => console.error(err)
    });
  }

  aplicarFiltros(): void {
    let resultado = [...this.citas];

    if (this.terminoBusqueda.trim()) {
      const termino = this.terminoBusqueda.toLowerCase();
      resultado = resultado.filter(c => {
        const paciente = `${c.paciente.nombre} ${c.paciente.apellidoPaterno} ${c.paciente.apellidoMaterno}`.toLowerCase();
        const medico = `${c.medico.nombre} ${c.medico.apellidoPaterno} ${c.medico.apellidoMaterno}`.toLowerCase();
        const dni = c.paciente.dni.toLowerCase();
        return paciente.includes(termino) || medico.includes(termino) || dni.includes(termino);
      });
    }

    if (this.filtroEstado) {
      resultado = resultado.filter(c => c.estado === this.filtroEstado);
    }

    if (this.filtroMedico) {
      resultado = resultado.filter(c => c.medico.id === this.filtroMedico);
    }

    if (this.filtroFechaInicio) {
      resultado = resultado.filter(c =>
        new Date(c.fechaHora) >= this.filtroFechaInicio!
      );
    }

    if (this.filtroFechaFin) {
      const fechaFin = new Date(this.filtroFechaFin);
      fechaFin.setHours(23, 59, 59, 999);
      resultado = resultado.filter(c =>
        new Date(c.fechaHora) <= fechaFin
      );
    }

    this.citasFiltradas = resultado;
    this.cdr.markForCheck();
  }

  limpiarFiltros(): void {
    this.terminoBusqueda = '';
    this.filtroEstado = null;
    this.filtroMedico = null;
    this.filtroFechaInicio = null;
    this.filtroFechaFin = null;
    this.aplicarFiltros();
  }

  abrirNuevaCita(): void {
    this.modoEdicion = false;
    this.resetFormulario();
    this.mostrarModal = true;
  }

  verDetalle(cita: Cita): void {
    this.citaSeleccionada = cita;
    this.mostrarDetalleModal = true;
  }

  abrirReprogramar(cita: Cita): void {
    this.citaSeleccionada = cita;
    this.nuevaFechaReprogramacion = new Date(cita.fechaHora);
    this.mostrarReprogramarModal = true;
  }

  guardarCita(): void {
    if (!this.nuevaCita.pacienteId) {
      this.toast.warn('Seleccione un paciente');
      return;
    }
    if (!this.nuevaCita.medicoId) {
      this.toast.warn('Seleccione un médico');
      return;
    }
    if (!this.nuevaCita.consultorioId) {
      this.toast.warn('Seleccione un consultorio');
      return;
    }
    if (!this.fechaHoraCita) {
      this.toast.warn('Seleccione fecha y hora');
      return;
    }
    if (!this.nuevaCita.motivoConsulta.trim()) {
      this.toast.warn('Ingrese el motivo de consulta');
      return;
    }

    this.nuevaCita.fechaHora = this.fechaHoraCita.toISOString();

    this.citaService.registrar(this.nuevaCita).subscribe({
      next: (res) => {
        this.toast.success(res);
        this.mostrarModal = false;
        this.cargarCitas();
        this.resetFormulario();
      },
      error: (err) => {
        console.error(err);
        const mensaje = err.error || 'Error al registrar la cita';
        this.toast.error(mensaje);
      }
    });
  }

  reprogramarCita(): void {
    if (!this.citaSeleccionada || !this.nuevaFechaReprogramacion) {
      this.toast.warn('Seleccione una nueva fecha y hora');
      return;
    }

    const fechaISO = this.nuevaFechaReprogramacion.toISOString();

    this.citaService.reprogramar(this.citaSeleccionada.id, fechaISO).subscribe({
      next: (res) => {
        this.toast.success(res);
        this.mostrarReprogramarModal = false;
        this.cargarCitas();
      },
      error: (err) => {
        console.error(err);
        const mensaje = err.error || 'Error al reprogramar';
        this.toast.error(mensaje);
      }
    });
  }

  cancelarCita(cita: Cita): void {
    if (!confirm('¿Está seguro de cancelar esta cita?')) return;

    this.citaService.cancelar(cita.id).subscribe({
      next: (res) => {
        this.toast.success(res);
        this.cargarCitas();
      },
      error: (err) => {
        console.error(err);
        const mensaje = err.error || 'Error al cancelar';
        this.toast.error(mensaje);
      }
    });
  }

  completarCita(cita: Cita): void {
    this.citaService.completar(cita.id).subscribe({
      next: (res) => {
        this.toast.success(res);
        this.cargarCitas();
      },
      error: (err) => {
        console.error(err);
        const mensaje = err.error || 'Error al completar';
        this.toast.error(mensaje);
      }
    });
  }

  marcarNoAsistio(cita: Cita): void {
    this.citaService.noAsistio(cita.id).subscribe({
      next: (res) => {
        this.toast.success(res);
        this.cargarCitas();
      },
      error: (err) => {
        console.error(err);
        const mensaje = err.error || 'Error al actualizar';
        this.toast.error(mensaje);
      }
    });
  }

  resetFormulario(): void {
    this.nuevaCita = {
      pacienteId: 0,
      medicoId: 0,
      consultorioId: 0,
      fechaHora: '',
      motivoConsulta: ''
    };
    this.fechaHoraCita = null;
  }

  obtenerNombreCompleto(persona: any): string {
    return `${persona.nombre} ${persona.apellidoPaterno} ${persona.apellidoMaterno}`;
  }

  obtenerClaseEstado(estado: EstadoCita): string {
    const clases = {
      [EstadoCita.PROGRAMADA]: 'bg-teal-light text-teal-dark',
      [EstadoCita.ATENDIDA]: 'bg-green-bg text-green-text',
      [EstadoCita.CANCELADA]: 'bg-red-bg text-red-text',
      [EstadoCita.NO_ASISTIO]: 'bg-amber-bg text-amber-text'
    };
    return clases[estado] || '';
  }

  puedeReprogramar(cita: Cita): boolean {
    return cita.estado === EstadoCita.PROGRAMADA;
  }

  puedeCancelar(cita: Cita): boolean {
    return cita.estado === EstadoCita.PROGRAMADA;
  }

  puedeCompletar(cita: Cita): boolean {
    return cita.estado === EstadoCita.PROGRAMADA;
  }

  puedeMarcarNoAsistio(cita: Cita): boolean {
    return cita.estado === EstadoCita.PROGRAMADA;
  }
}
