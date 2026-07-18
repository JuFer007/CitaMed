import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Table, TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';
import { GlobalToast } from '../../../core/services/global-toast';
import { SortEvent } from 'primeng/api';
import { environment } from '../../../../environments/environment';

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
  fechaPago?: string;
}

@Component({
  selector: 'app-pagos-component',
  standalone: true,
  imports: [CommonModule, FormsModule, TableModule, InputTextModule],
  templateUrl: './pagos-component.html',
  styleUrl: './pagos-component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PagosComponent implements OnInit {
  @ViewChild('dt') dt!: Table;
  initialValue: PagoDetalle[] = [];
  isSorted: boolean | null = null;
  private resetting = false;

  pagos: PagoDetalle[] = [];
  pagosFiltrados: PagoDetalle[] = [];
  loading = false;
  generandoTicketPdf = false;

  terminoBusqueda = '';
  filtroEstado: string | null = null;
  filtroMetodo: string | null = null;
  filtroFechaInicio = '';
  filtroFechaFin = '';

  private apiUrl = `${environment.apiUrl}/api`;

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
        this.aplicarFiltros();
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

  aplicarFiltros(): void {
    let res = [...this.pagos];

    if (this.terminoBusqueda.trim()) {
      const t = this.terminoBusqueda.toLowerCase();
      res = res.filter(
        (p) =>
          p.paciente.toLowerCase().includes(t) ||
          p.dni.toLowerCase().includes(t),
      );
    }

    if (this.filtroEstado) {
      res = res.filter((p) => p.estado === this.filtroEstado);
    }

    if (this.filtroMetodo) {
      res = res.filter((p) => p.metodoPago === this.filtroMetodo);
    }

    if (this.filtroFechaInicio) {
      const inicio = new Date(this.filtroFechaInicio);
      res = res.filter((p) => new Date(p.fechaHora) >= inicio);
    }

    if (this.filtroFechaFin) {
      const fin = new Date(this.filtroFechaFin);
      fin.setHours(23, 59, 59, 999);
      res = res.filter((p) => new Date(p.fechaHora) <= fin);
    }

    this.pagosFiltrados = res;
    this.initialValue = [...res];
    this.isSorted = null;
    this.cdr.markForCheck();
  }

  limpiarFiltros(): void {
    this.terminoBusqueda = '';
    this.filtroEstado = null;
    this.filtroMetodo = null;
    this.filtroFechaInicio = '';
    this.filtroFechaFin = '';
    this.aplicarFiltros();
  }

  descargarTicket(pago: PagoDetalle): void {
    this.generandoTicketPdf = true;
    this.cdr.markForCheck();

    const fechaCita = new Date(pago.fechaHora);
    const fechaPago = pago.fechaPago ? new Date(pago.fechaPago) : null;
    const ticketData = {
      cliente: pago.paciente,
      dni: pago.dni,
      fecha: fechaCita.toLocaleDateString('es-PE', { year: 'numeric', month: 'long', day: 'numeric' }),
      hora: fechaCita.toLocaleTimeString('es-PE', { hour: '2-digit', minute: '2-digit' }),
      numeroCita: pago.citaId.toString(),
      medico: pago.medico,
      especialidad: pago.especialidad,
      metodoPago: pago.metodoPago,
      monto: pago.monto,
      fechaPago: fechaPago ? fechaPago.toLocaleDateString('es-PE', { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' }) : null,
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
    if (this.resetting) return;

    if (this.isSorted == null) {
      this.isSorted = true;
      this.sortTableData(event);
    } else if (this.isSorted === true) {
      this.isSorted = false;
      this.sortTableData(event);
    } else {
      this.isSorted = null;
      this.resetting = true;
      this.pagosFiltrados = [...this.initialValue];
      this.dt.reset();
      setTimeout(() => { this.resetting = false; }, 0);
    }
  }

  private sortTableData(event: SortEvent): void {
    this.pagosFiltrados.sort((data1, data2) => {
      const value1 = (data1 as any)[event.field!];
      const value2 = (data2 as any)[event.field!];
      let result: number;
      if (value1 == null && value2 != null) result = -1;
      else if (value1 != null && value2 == null) result = 1;
      else if (value1 == null && value2 == null) result = 0;
      else if (typeof value1 === 'string' && typeof value2 === 'string')
        result = value1.localeCompare(value2);
      else result = value1 < value2 ? -1 : value1 > value2 ? 1 : 0;
      return event.order! * result;
    });
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

  formatearFecha(fecha: string): string {
    if (!fecha) return '—';
    const d = new Date(fecha);
    return d.toLocaleDateString('es-PE', { day: 'numeric', month: 'short', year: 'numeric', hour: '2-digit', minute: '2-digit' });
  }
}
