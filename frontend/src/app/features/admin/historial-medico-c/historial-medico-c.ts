import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { PacienteService } from '../../../core/services/paciente-service';
import { HistorialMedicoService } from '../../../core/services/historial-medico-service';
import { Paciente, PageResponse } from '../../../model/Paciente';
import { HistorialMedicoDetalle, CitaHistorial } from '../../../model/HistorialMedico';
import { GlobalToast } from '../../../core/services/global-toast';
import { EstadoCita } from '../../../model/Cita';

@Component({
  selector: 'app-historial-medico-c',
  standalone: true,
  imports: [CommonModule, FormsModule, DialogModule],
  templateUrl: './historial-medico-c.html',
  styleUrl: './historial-medico-c.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class HistorialMedicoC implements OnInit {
  pacientes: Paciente[] = [];
  totalRecords = 0;
  page = 0;
  size = 15;
  loading = false;
  terminoBusqueda = '';

  mostrarHistorial = false;
  historial: HistorialMedicoDetalle | null = null;
  cargandoHistorial = false;

  mostrarDetalle = false;
  citaSeleccionada: CitaHistorial | null = null;

  EstadoCita = EstadoCita;
  Math = Math;

  get totalPages(): number {
    return Math.ceil(this.totalRecords / this.size);
  }

  private pdfApi = 'http://localhost:3005/generar-historial';

  constructor(
    private pacienteService: PacienteService,
    private historialMedicoService: HistorialMedicoService,
    private toast: GlobalToast,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.buscarPacientes();
  }

  getInitials(nombre: string, apellidoPaterno: string, apellidoMaterno: string): string {
    return ((nombre?.charAt(0) || '') + (apellidoPaterno?.charAt(0) || '')).toUpperCase();
  }

  buscarPacientes(page: number = 0): void {
    this.loading = true;
    this.page = page;
    this.pacienteService.listar(page, this.size, this.terminoBusqueda).subscribe({
      next: (res: PageResponse<Paciente>) => {
        this.pacientes = res.content;
        this.totalRecords = res.totalElements;
        this.loading = false;
        this.cdr.markForCheck();
      },
      error: () => {
        this.toast.error('No se pudieron cargar los pacientes');
        this.loading = false;
        this.cdr.markForCheck();
      },
    });
  }

  onPage(event: any): void {
    const first = Number(event?.first ?? 0);
    const rows = Number(event?.rows ?? this.size ?? 15);
    const p = rows > 0 ? Math.floor(first / rows) : 0;
    this.buscarPacientes(p);
  }

  abrirHistorial(paciente: Paciente): void {
    if (!paciente.id) return;
    this.cargandoHistorial = true;
    this.historial = null;
    this.mostrarHistorial = true;
    this.cdr.markForCheck();

    this.historialMedicoService.obtenerHistorialCompleto(paciente.id).subscribe({
      next: (res) => {
        this.historial = res;
        this.cargandoHistorial = false;
        this.cdr.markForCheck();
      },
      error: () => {
        this.toast.error('No se pudo cargar el historial médico');
        this.cargandoHistorial = false;
        this.mostrarHistorial = false;
        this.cdr.markForCheck();
      },
    });
  }

  descargarHistorial(paciente: { id?: number }): void {
    const id = paciente.id;
    if (!id) { this.toast.error('ID de paciente no disponible'); return; }
    this.historialMedicoService.obtenerHistorialCompleto(id).subscribe({
      next: (h) => this.generarPdf(h),
      error: () => { this.toast.error('Error al obtener datos del historial'); this.cdr.markForCheck(); },
    });
  }

  descargarHistorialActual(): void {
    if (!this.historial) { this.toast.error('No hay historial cargado'); return; }
    this.generarPdf(this.historial);
  }

  private generarPdf(historial: HistorialMedicoDetalle): void {
    this.toast.info('Generando historial médico...');
    const edad = historial.paciente.fechaNacimiento
      ? Math.floor((Date.now() - new Date(historial.paciente.fechaNacimiento).getTime()) / (365.25 * 24 * 60 * 60 * 1000)).toString()
      : '—';

    const citas = historial.citas.map(c => ({
      fecha: new Date(c.fechaHora).toLocaleDateString('es-PE', { year: 'numeric', month: 'long', day: 'numeric' }),
      medico: `${c.medicoNombre} ${c.medicoApellidoPaterno} ${c.medicoApellidoMaterno}`,
      especialidad: c.medicoEspecialidad,
      motivo: c.motivoConsulta,
      estado: c.estado,
    }));

    const diagnosticos = historial.citas
      .filter(c => c.diagnostico)
      .map(c => ({
        enfermedad: c.diagnostico!.enfermedad,
        descripcion: c.diagnostico!.descripcion || '',
        receta: c.diagnostico!.receta || '',
        indicaciones: c.diagnostico!.indicaciones || '',
      }));

    const data = {
      paciente: `${historial.paciente.nombre} ${historial.paciente.apellidoPaterno} ${historial.paciente.apellidoMaterno}`,
      dni: historial.paciente.dni,
      edad,
      genero: historial.paciente.genero || '—',
      grupoSanguineo: historial.paciente.grupoSanguineo || '—',
      telefono: historial.paciente.telefono || '—',
      email: historial.paciente.email || '—',
      totalCitas: historial.citas.length.toString(),
      citas,
      diagnosticos,
    };

    const form = document.createElement('form');
    form.method = 'POST';
    form.action = this.pdfApi;
    form.target = '_blank';
    form.style.display = 'none';

    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'data';
    input.value = JSON.stringify(data);
    form.appendChild(input);

    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
    this.cdr.markForCheck();
  }

  verDetalleCita(cita: CitaHistorial): void {
    this.citaSeleccionada = cita;
    this.mostrarDetalle = true;
    this.cdr.markForCheck();
  }

  estadoClass(estado: EstadoCita): string {
    switch (estado) {
      case EstadoCita.ATENDIDA: return 'badge-atendida';
      case EstadoCita.PROGRAMADA: return 'badge-programada';
      case EstadoCita.CANCELADA: return 'badge-cancelada';
      case EstadoCita.NO_ASISTIO: return 'badge-noasistio';
      default: return '';
    }
  }
}
