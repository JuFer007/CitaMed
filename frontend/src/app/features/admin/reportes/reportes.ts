import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration } from 'chart.js';
import { forkJoin, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import {
  ReporteService,
  ReporteMedico,
} from '../../../core/services/reporte-service';

@Component({
  selector: 'app-reportes',
  standalone: true,
  imports: [CommonModule, FormsModule, BaseChartDirective],
  templateUrl: './reportes.html',
  styleUrl: './reportes.css',
})
export class Reportes implements OnInit {
  aniosDisponibles: number[] = [];
  anioSeleccionado: number = new Date().getFullYear();

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
    this.aniosDisponibles = [actual, actual - 1, actual - 2];
    this.cargarTodo();
  }

  cambiarAnio(anio: number | string): void {
    this.anioSeleccionado = Number(anio);
    this.cargarTodo();
  }

  cargarTodo(): void {
    this.loading = true;
    this.error = false;
    this.errorMsg = '';

    forkJoin({
      citasPorMes: this.reporteService.citasPorMes(this.anioSeleccionado).pipe(
        catchError(err => {
          console.error('Error cargando citas por mes:', err);
          return of([]);
        })
      ),
      ingresosPorMes: this.reporteService.ingresosPorMes(this.anioSeleccionado).pipe(
        catchError(err => {
          console.error('Error cargando ingresos por mes:', err);
          return of([]);
        })
      ),
      citasPorEstado: this.reporteService.citasPorEstado(this.anioSeleccionado).pipe(
        catchError(err => {
          console.error('Error cargando citas por estado:', err);
          return of([]);
        })
      ),
      citasPorEspecialidad: this.reporteService.citasPorEspecialidad(this.anioSeleccionado).pipe(
        catchError(err => {
          console.error('Error cargando citas por especialidad:', err);
          return of([]);
        })
      ),
      topMedicos: this.reporteService.topMedicos(this.anioSeleccionado).pipe(
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
          this.errorMsg = 'No se encontraron datos para el año seleccionado.';
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
