import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration } from 'chart.js';
import { forkJoin, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SelectModule } from 'primeng/select';
import { DatePickerModule } from 'primeng/datepicker';
import {
  ReporteService,
  ReportFilter,
  ReporteMedico,
} from '../../../core/services/reporte-service';

interface SelectOption {
  label: string;
  value: number | null;
}

@Component({
  selector: 'app-reportes',
  standalone: true,
  imports: [CommonModule, FormsModule, BaseChartDirective, SelectModule, DatePickerModule],
  templateUrl: './reportes.html',
  styleUrl: './reportes.css',
})
export class Reportes implements OnInit {
  aniosDisponibles: SelectOption[] = [];
  anioSeleccionado: number | null = new Date().getFullYear();

  mesesDisponibles: SelectOption[] = [
    { label: 'Todos', value: null },
    { label: 'Enero', value: 1 },
    { label: 'Febrero', value: 2 },
    { label: 'Marzo', value: 3 },
    { label: 'Abril', value: 4 },
    { label: 'Mayo', value: 5 },
    { label: 'Junio', value: 6 },
    { label: 'Julio', value: 7 },
    { label: 'Agosto', value: 8 },
    { label: 'Setiembre', value: 9 },
    { label: 'Octubre', value: 10 },
    { label: 'Noviembre', value: 11 },
    { label: 'Diciembre', value: 12 },
  ];
  mesSeleccionado: number | null = null;

  fechaDesde: Date | null = null;
  fechaHasta: Date | null = null;

  filtroLabel = '';

  loading = false;
  error = false;
  errorMsg = '';

  totalCitasAnio = 0;
  totalIngresosAnio = 0;
  topMedicos: ReporteMedico[] = [];

  citasPorMesData: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: [{
      data: [],
      label: 'Citas',
      fill: true,
      tension: 0.35,
      borderColor: '#0f8b86',
      backgroundColor: 'rgba(15,139,134,0.15)',
      pointBackgroundColor: '#0f8b86',
    }],
  };
  citasPorMesOptions: ChartConfiguration<'line'>['options'] = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: { legend: { display: false } },
    scales: { y: { beginAtZero: true, ticks: { precision: 0 } } },
  };

  ingresosPorMesData: ChartConfiguration<'bar'>['data'] = {
    labels: [],
    datasets: [{ data: [], label: 'Ingresos (S/)', backgroundColor: '#36b1ae', borderRadius: 6 }],
  };
  ingresosPorMesOptions: ChartConfiguration<'bar'>['options'] = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: { legend: { display: false } },
    scales: { y: { beginAtZero: true } },
  };

  citasPorEstadoData: ChartConfiguration<'doughnut'>['data'] = {
    labels: [],
    datasets: [{ data: [], backgroundColor: ['#0f8b86', '#3b6d11', '#a32d2d', '#ba7517'] }],
  };
  citasPorEstadoOptions: ChartConfiguration<'doughnut'>['options'] = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: { legend: { position: 'bottom' } },
  };

  citasPorEspecialidadData: ChartConfiguration<'bar'>['data'] = {
    labels: [],
    datasets: [{ data: [], backgroundColor: '#2d9694', borderRadius: 6 }],
  };
  citasPorEspecialidadOptions: ChartConfiguration<'bar'>['options'] = {
    indexAxis: 'y',
    responsive: true,
    maintainAspectRatio: false,
    plugins: { legend: { display: false } },
  };

  constructor(
    private reporteService: ReporteService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    const actual = new Date().getFullYear();
    this.aniosDisponibles = [
      { label: String(actual), value: actual },
      { label: String(actual - 1), value: actual - 1 },
      { label: String(actual - 2), value: actual - 2 },
      { label: String(actual - 3), value: actual - 3 },
      { label: String(actual - 4), value: actual - 4 },
      { label: String(actual - 5), value: actual - 5 },
    ];
    this.actualizarFiltroLabel();
    this.cargarTodo();
  }

  onFilterChange(): void {
    if (this.fechaDesde && this.fechaHasta) {
      this.mesSeleccionado = null;
    } else if (this.mesSeleccionado) {
      this.fechaDesde = null;
      this.fechaHasta = null;
    }
    this.actualizarFiltroLabel();
    this.cargarTodo();
  }

  limpiarFiltros(): void {
    this.anioSeleccionado = new Date().getFullYear();
    this.mesSeleccionado = null;
    this.fechaDesde = null;
    this.fechaHasta = null;
    this.actualizarFiltroLabel();
    this.cargarTodo();
  }

  private actualizarFiltroLabel(): void {
    if (this.fechaDesde && this.fechaHasta) {
      const d = (dt: Date) =>
        `${String(dt.getDate()).padStart(2, '0')}/${String(dt.getMonth() + 1).padStart(2, '0')}/${dt.getFullYear()}`;
      this.filtroLabel = `${d(this.fechaDesde)} - ${d(this.fechaHasta)}`;
    } else if (this.mesSeleccionado) {
      const mes = this.mesesDisponibles.find(m => m.value === this.mesSeleccionado);
      this.filtroLabel = `${mes?.label ?? ''} ${this.anioSeleccionado ?? ''}`;
    } else if (this.anioSeleccionado) {
      this.filtroLabel = String(this.anioSeleccionado);
    } else {
      this.filtroLabel = '';
    }
  }

  private buildFilter(): ReportFilter {
    if (this.fechaDesde && this.fechaHasta) {
      return {
        fechaInicio: this.fechaDesde.toISOString().split('T')[0],
        fechaFin: this.fechaHasta.toISOString().split('T')[0],
      };
    }
    if (this.mesSeleccionado) {
      return {
        anio: this.anioSeleccionado,
        mes: this.mesSeleccionado,
      };
    }
    return { anio: this.anioSeleccionado };
  }

  cargarTodo(): void {
    this.loading = true;
    this.error = false;
    this.errorMsg = '';
    const filter = this.buildFilter();

    forkJoin({
      citasPorMes: this.reporteService.citasPorMes(filter).pipe(
        catchError(err => {
          console.error('Error cargando citas por mes:', err);
          return of([]);
        })
      ),
      ingresosPorMes: this.reporteService.ingresosPorMes(filter).pipe(
        catchError(err => {
          console.error('Error cargando ingresos por mes:', err);
          return of([]);
        })
      ),
      citasPorEstado: this.reporteService.citasPorEstado(filter).pipe(
        catchError(err => {
          console.error('Error cargando citas por estado:', err);
          return of([]);
        })
      ),
      citasPorEspecialidad: this.reporteService.citasPorEspecialidad(filter).pipe(
        catchError(err => {
          console.error('Error cargando citas por especialidad:', err);
          return of([]);
        })
      ),
      topMedicos: this.reporteService.topMedicos(filter).pipe(
        catchError(err => {
          console.error('Error cargando top médicos:', err);
          return of([]);
        })
      ),
    }).subscribe({
      next: (result) => {
        this.actualizarCitasPorMes(result.citasPorMes);
        this.actualizarIngresosPorMes(result.ingresosPorMes);
        this.actualizarCitasPorEstado(result.citasPorEstado);
        this.actualizarCitasPorEspecialidad(result.citasPorEspecialidad);
        this.topMedicos = result.topMedicos;

        const totalSinDatos =
          result.citasPorMes.length === 0 &&
          result.ingresosPorMes.length === 0 &&
          result.citasPorEstado.length === 0;

        if (totalSinDatos) {
          this.error = true;
          this.errorMsg = 'No se encontraron datos para los filtros seleccionados.';
        }

        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error general en reportes:', err);
        this.loading = false;
        this.error = true;
        this.errorMsg = 'Error al cargar los reportes. Intente nuevamente.';
        this.cdr.detectChanges();
      },
    });
  }

  private actualizarCitasPorMes(data: { mes: string; total: number }[]): void {
    this.citasPorMesData = {
      ...this.citasPorMesData,
      labels: data.map((d) => d.mes),
      datasets: [{ ...this.citasPorMesData.datasets[0], data: data.map((d) => d.total) }],
    };
    this.totalCitasAnio = data.reduce((acc, d) => acc + d.total, 0);
  }

  private actualizarIngresosPorMes(data: { mes: string; total: number }[]): void {
    this.ingresosPorMesData = {
      ...this.ingresosPorMesData,
      labels: data.map((d) => d.mes),
      datasets: [{ ...this.ingresosPorMesData.datasets[0], data: data.map((d) => d.total) }],
    };
    this.totalIngresosAnio = data.reduce((acc, d) => acc + d.total, 0);
  }

  private actualizarCitasPorEstado(data: { estado: string; total: number }[]): void {
    this.citasPorEstadoData = {
      ...this.citasPorEstadoData,
      labels: data.map((d) => this.formatearEstado(d.estado)),
      datasets: [{ ...this.citasPorEstadoData.datasets[0], data: data.map((d) => d.total) }],
    };
  }

  private actualizarCitasPorEspecialidad(data: { especialidad: string; total: number }[]): void {
    this.citasPorEspecialidadData = {
      ...this.citasPorEspecialidadData,
      labels: data.map((d) => d.especialidad),
      datasets: [{ ...this.citasPorEspecialidadData.datasets[0], data: data.map((d) => d.total) }],
    };
  }

  reintentar(): void {
    this.cargarTodo();
  }

  formatearEstado(estado: string): string {
    const nombres: Record<string, string> = {
      PROGRAMADA: 'Programadas',
      ATENDIDA: 'Atendidas',
      CANCELADA: 'Canceladas',
      NO_ASISTIO: 'No asistió',
    };
    return nombres[estado] ?? estado;
  }
}
