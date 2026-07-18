import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PacientePortalService } from '../../../core/services/paciente-portal-service';
import { GlobalToast } from '../../../core/services/global-toast';
import { HistorialMedicoService } from '../../../core/services/historial-medico-service';

interface TimelineItem {
  fecha: string;
  hora: string;
  titulo: string;
  descripcion: string;
  medico: string;
  tipo: string;
  estado: string;
  rawFecha: string;
}

@Component({
  selector: 'app-historial-paciente',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './historial-paciente.html',
  styleUrl: './historial-paciente.css',
})
export class HistorialPacienteComponent implements OnInit {
  items: TimelineItem[] = [];
  cargado = false;
  errorCarga = false;
  descargando = false;
  private historialRaw: any = null;

  constructor(
    private portalService: PacientePortalService,
    private historialMedicoService: HistorialMedicoService,
    private toast: GlobalToast,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.portalService.obtenerHistorialClinico().subscribe({
      next: (data: any) => {
        this.historialRaw = data;
        this.items = (data.citas || []).map((c: any) => ({
          fecha: c.fechaHora
            ? new Date(c.fechaHora).toLocaleDateString('es-PE', {
                day: 'numeric',
                month: 'short',
                year: 'numeric',
              })
            : '—',
          hora: c.fechaHora
            ? new Date(c.fechaHora).toLocaleTimeString('es-PE', {
                hour: '2-digit',
                minute: '2-digit',
              })
            : '',
          titulo: c.motivoConsulta || 'Consulta médica',
          descripcion: c.diagnostico?.enfermedad
            ? `Diagnóstico: ${c.diagnostico.enfermedad}${c.diagnostico.descripcion ? ' — ' + c.diagnostico.descripcion : ''}`
            : c.estado === 'PROGRAMADA'
              ? 'Cita programada'
              : 'Sin diagnóstico registrado',
          medico: `${c.medicoNombre} ${c.medicoApellidoPaterno}`,
          tipo: c.medicoEspecialidad || 'General',
          estado: c.estado,
          rawFecha: c.fechaHora,
        }));
        this.cargado = true;
        this.cdr.markForCheck();
      },
      error: () => {
        this.cargado = true;
        this.cdr.markForCheck();
        this.errorCarga = true;
      },
    });
  }

  descargarHistorial(): void {
    if (!this.historialRaw) {
      this.toast.error('No hay datos de historial para descargar');
      return;
    }
    this.descargando = true;
    this.toast.info('Generando historial PDF...', { life: 999999 });

    const h = this.historialRaw;
    const edad = h.paciente?.fechaNacimiento
      ? Math.floor((Date.now() - new Date(h.paciente.fechaNacimiento).getTime()) / (365.25 * 24 * 60 * 60 * 1000)).toString()
      : '—';

    const citas = (h.citas || []).map((c: any) => ({
      fecha: new Date(c.fechaHora).toLocaleDateString('es-PE', { year: 'numeric', month: 'long', day: 'numeric' }),
      medico: `${c.medicoNombre} ${c.medicoApellidoPaterno} ${c.medicoApellidoMaterno || ''}`,
      especialidad: c.medicoEspecialidad,
      motivo: c.motivoConsulta,
      estado: c.estado,
    }));

    const diagnosticos = (h.citas || [])
      .filter((c: any) => c.diagnostico)
      .map((c: any) => ({
        enfermedad: c.diagnostico.enfermedad,
        descripcion: c.diagnostico.descripcion || '',
        receta: c.diagnostico.receta || '',
        indicaciones: c.diagnostico.indicaciones || '',
      }));

    const data = {
      paciente: `${h.paciente?.nombre || ''} ${h.paciente?.apellidoPaterno || ''} ${h.paciente?.apellidoMaterno || ''}`.trim(),
      dni: h.paciente?.dni || '—',
      edad,
      genero: h.paciente?.genero || '—',
      grupoSanguineo: h.paciente?.grupoSanguineo || '—',
      telefono: h.paciente?.telefono || '—',
      email: h.paciente?.email || '—',
      totalCitas: h.citas?.length?.toString() || '0',
      citas,
      diagnosticos,
    };

    this.historialMedicoService.descargarHistorial(data).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        window.open(url, '_blank');
        this.descargando = false;
        this.toast.clear();
        this.toast.success('Historial descargado correctamente');
      },
      error: () => {
        this.descargando = false;
        this.toast.clear();
        this.toast.error('Error al generar el historial PDF');
      },
    });
  }
}
