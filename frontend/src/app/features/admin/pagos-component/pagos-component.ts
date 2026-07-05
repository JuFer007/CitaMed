import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { TableModule } from 'primeng/table';
import { GlobalToast } from '../../../core/services/global-toast';
import { SortEvent } from 'primeng/api';

interface PagoDetalle {
  id: number;
  paciente: string;
  dni: string;
  fechaHora: string;
  citaId: number;
  medico: string;
  especialidad: string;
  metodoPago: string;
  monto: number;
  estado: string;
}

@Component({
  selector: 'app-pagos-component',
  standalone: true,
  imports: [CommonModule, TableModule],
  templateUrl: './pagos-component.html',
  styleUrl: './pagos-component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PagosComponent implements OnInit {
  pagos: PagoDetalle[] = [];
  loading = false;
  generandoTicketPdf = false;

  private apiUrl = 'http://localhost:8080/api';

  constructor(
    private http: HttpClient,
    private toast: GlobalToast,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.cargarPagos();
  }

  cargarPagos(): void {
    this.loading = true;
    this.cdr.markForCheck();
    this.http.get<PagoDetalle[]>(`${this.apiUrl}/pago/detalle`).subscribe({
      next: (data) => {
        this.pagos = data;
        this.loading = false;
        this.cdr.markForCheck();
      },
      error: () => {
        this.toast.error('No se pudieron cargar los pagos');
        this.loading = false;
        this.cdr.markForCheck();
      },
    });
  }

  descargarTicket(pago: PagoDetalle): void {
    this.generandoTicketPdf = true;
    this.cdr.markForCheck();

    const fecha = new Date(pago.fechaHora);
    const ticketData = {
      cliente: pago.paciente,
      dni: pago.dni,
      fecha: fecha.toLocaleDateString('es-PE', { year: 'numeric', month: 'long', day: 'numeric' }),
      hora: fecha.toLocaleTimeString('es-PE', { hour: '2-digit', minute: '2-digit' }),
      numeroCita: pago.citaId.toString(),
      medico: pago.medico,
      especialidad: pago.especialidad,
      metodoPago: pago.metodoPago,
      monto: pago.monto,
    };

    this.http.post(`${this.apiUrl}/pdf/ticket`, ticketData, { responseType: 'blob' }).subscribe({
      next: (blob) => {
        const url = window.URL.createObjectURL(blob);
        window.open(url, '_blank');
        this.generandoTicketPdf = false;
        this.cdr.markForCheck();
      },
      error: () => {
        this.toast.error('Error al generar el ticket de pago');
        this.generandoTicketPdf = false;
        this.cdr.markForCheck();
      },
    });
  }

  customSort(event: SortEvent): void {
    const data = [...this.pagos];
    const field = event.field as keyof PagoDetalle;
    data.sort((a, b) => {
      const va = a[field];
      const vb = b[field];
      const result = va < vb ? -1 : va > vb ? 1 : 0;
      return event.order! * result;
    });
    this.pagos = data;
    this.cdr.markForCheck();
  }

  metodoPagoLabel(metodo: string): string {
    const map: Record<string, string> = {
      EFECTIVO: 'Efectivo',
      TARJETA: 'Tarjeta',
      TRANSFERENCIA: 'Transferencia',
      SEGURO: 'Seguro',
      NO_REGISTRADO: 'No registrado',
    };
    return map[metodo] ?? metodo;
  }

  estadoClass(estado: string): string {
    const map: Record<string, string> = {
      PAGADO: 'pg-badge-pagado',
      PENDIENTE: 'pg-badge-pendiente',
      ANULADO: 'pg-badge-anulado',
      REEMBOLSADO: 'pg-badge-reembolsado',
    };
    return map[estado] ?? '';
  }
}
