import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GlobalToast } from '../../../core/services/global-toast';
import { PacientePortalService, PortalPago } from '../../../core/services/paciente-portal-service';

@Component({
  selector: 'app-pagos-paciente',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pagos-paciente.html',
  styleUrl: './pagos-paciente.css',
})
export class PagosPacienteComponent implements OnInit {
  pagos: PortalPago[] = [];

  constructor(
    private portalService: PacientePortalService,
    private toast: GlobalToast
  ) {}

  ngOnInit(): void {
    this.portalService.obtenerPagos().subscribe({
      next: (data) => { this.pagos = data; },
    });
  }

  pagarAhora(item: PortalPago): void {
    this.toast.success('Redirigiendo a pasarela de pago...');
  }

  descargarTicket(item: PortalPago): void {
    this.toast.success('Descargando comprobante...');
  }

  estadoClase(estado: string): string {
    const map: Record<string, string> = {
      PENDIENTE: 'bg-[#faeeda] text-[#ba7517]',
      PAGADO: 'bg-[#eaf3de] text-[#3b6d11]',
      VENCIDO: 'bg-[#fcebeb] text-[#a32d2d]',
      ANULADO: 'bg-slate-100 text-slate-500',
    };
    return map[estado] || 'bg-slate-100 text-slate-500';
  }

  formatearFecha(fecha: string): string {
    if (!fecha) return '—';
    const d = new Date(fecha);
    return d.toLocaleDateString('es-PE', { day: 'numeric', month: 'short', year: 'numeric' });
  }
}
