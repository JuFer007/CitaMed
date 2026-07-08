import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PacientePortalService, PortalCita, PortalDiagnostico } from '../../../core/services/paciente-portal-service';
import { GlobalToast } from '../../../core/services/global-toast';

@Component({
  selector: 'app-citas-paciente',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './citas-paciente.html',
  styleUrl: './citas-paciente.css',
})
export class CitasPacienteComponent implements OnInit {
  proximas: PortalCita[] = [];
  historial: PortalCita[] = [];
  vista = 'proximas';
  modalAbierto = false;
  citaModal: PortalCita | null = null;
  diagnosticoModal: PortalDiagnostico | null = null;

  constructor(
    private portalService: PacientePortalService,
    private toast: GlobalToast
  ) {}

  ngOnInit(): void {
    this.portalService.obtenerProximasCitas().subscribe({
      next: (data) => { this.proximas = data; },
    });
    this.portalService.obtenerHistorialCitas().subscribe({
      next: (data) => { this.historial = data; },
    });
  }

  verDetalle(cita: PortalCita): void {
    this.citaModal = cita;
    this.diagnosticoModal = null;
    this.modalAbierto = true;
  }

  verDiagnostico(citaId: number): void {
    this.portalService.obtenerDiagnostico(citaId).subscribe({
      next: (data) => {
        this.diagnosticoModal = data;
      },
      error: () => {
        this.toast.info('No hay diagnóstico disponible para esta cita');
      },
    });
  }

  cancelarCita(id: number): void {
    if (!confirm('¿Estás seguro de cancelar esta cita?')) return;
    this.portalService.cancelarCita(id).subscribe({
      next: () => {
        this.toast.success('Cita cancelada correctamente');
        this.cerrarModal();
        this.proximas = this.proximas.filter(c => c.id !== id);
      },
      error: () => this.toast.error('Error al cancelar la cita'),
    });
  }

  cerrarModal(): void {
    this.modalAbierto = false;
    this.citaModal = null;
    this.diagnosticoModal = null;
  }

  estadoClase(estado: string): string {
    const map: Record<string, string> = {
      PROGRAMADA: 'bg-[#36b1ae]/10 text-[#0f8b86]',
      CONFIRMADA: 'bg-[#36b1ae]/10 text-[#0f8b86]',
      ATENDIDA: 'bg-[#eaf3de] text-[#3b6d11]',
      CANCELADA: 'bg-slate-100 text-slate-500',
      NO_ASISTIO: 'bg-[#fcebeb] text-[#a32d2d]',
    };
    return map[estado] || 'bg-slate-100 text-slate-500';
  }

  formatearFecha(fecha: string): string {
    if (!fecha) return '';
    const d = new Date(fecha);
    return d.toLocaleDateString('es-PE', { day: 'numeric', month: 'short', year: 'numeric' });
  }
}
