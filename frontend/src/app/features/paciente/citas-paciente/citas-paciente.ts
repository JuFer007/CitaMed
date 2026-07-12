import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
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
  modalDiagnosticoAbierto = false;
  citaDiagnostico: PortalCita | null = null;
  cargandoDiagnostico = false;
  cargandoPdf = false;
  sinDiagnostico = false;

  constructor(
    private portalService: PacientePortalService,
    private toast: GlobalToast,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.portalService.obtenerProximasCitas().subscribe({
      next: (data) => { this.proximas = data; this.cdr.detectChanges(); },
    });
    this.portalService.obtenerHistorialCitas().subscribe({
      next: (data) => { this.historial = data; this.cdr.detectChanges(); },
    });
  }

  verDetalle(cita: PortalCita): void {
    this.citaModal = cita;
    this.modalAbierto = true;
  }

  verDiagnostico(cita: PortalCita): void {
    this.citaDiagnostico = cita;
    this.diagnosticoModal = null;
    this.sinDiagnostico = false;
    this.modalDiagnosticoAbierto = true;

    const diag = cita.diagnostico;
    if (diag) {
      this.diagnosticoModal = diag;
      return;
    }

    this.portalService.obtenerDiagnostico(cita.id).subscribe({
      next: (data) => {
        this.diagnosticoModal = data;
      },
      error: () => {
        this.sinDiagnostico = true;
      },
    });
  }

  descargarDiagnostico(): void {
    if (!this.citaDiagnostico) return;
    this.cargandoPdf = true;
    this.toast.info('Generando diagnóstico PDF...', { life: 999999 });
    this.portalService.descargarRecetaPdf(this.citaDiagnostico.id).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        window.open(url, '_blank');
        this.cargandoPdf = false;
        this.toast.clear();
        this.toast.success('Diagnóstico descargado');
      },
      error: () => {
        this.cargandoPdf = false;
        this.toast.clear();
        this.toast.error('Error al generar el diagnóstico PDF');
      },
    });
  }

  pagarAhora(): void {
    this.toast.info('Redirigiendo a pasarela de pago...');
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
  }

  cerrarModalDiagnostico(): void {
    this.modalDiagnosticoAbierto = false;
    this.citaDiagnostico = null;
    this.diagnosticoModal = null;
    this.sinDiagnostico = false;
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
