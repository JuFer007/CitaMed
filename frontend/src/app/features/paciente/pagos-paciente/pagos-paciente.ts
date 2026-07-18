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
  descargandoId: number | null = null;

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
    this.toast.info('Redirigiendo a pasarela de pago...');
  }

  descargarTicket(item: PortalPago): void {
    this.descargandoId = item.id;
    this.toast.info('Generando ticket PDF...', { life: 999999 });

    const fecha = new Date(item.fecha);
    const ticketData = {
      numeroCita: item.citaId.toString(),
      cliente: item.pacienteNombre,
      dni: item.dniPaciente,
      fecha: fecha.toLocaleDateString('es-PE', { year: 'numeric', month: 'long', day: 'numeric' }),
      hora: fecha.toLocaleTimeString('es-PE', { hour: '2-digit', minute: '2-digit' }),
      fechaPago: fecha.toLocaleDateString('es-PE', { year: 'numeric', month: 'long', day: 'numeric' }) + ' ' + fecha.toLocaleTimeString('es-PE', { hour: '2-digit', minute: '2-digit' }),
      medico: item.medico,
      especialidad: item.especialidad,
      metodoPago: item.metodoPago || '—',
      monto: item.monto,
      subtotal: item.monto,
      descuento: 0,
    };

    this.portalService.descargarTicketPdf(ticketData).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        window.open(url, '_blank');
        this.descargandoId = null;
        this.toast.clear();
        this.toast.success('Ticket descargado correctamente');
      },
      error: () => {
        this.descargandoId = null;
        this.toast.clear();
        this.toast.error('Error al generar el ticket PDF');
      },
    });
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
