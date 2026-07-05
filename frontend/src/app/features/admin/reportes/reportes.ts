import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration } from 'chart.js';
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

  constructor(private reporteService: ReporteService) {}

  ngOnInit(): void {
    const actual = new Date().getFullYear();
    this.aniosDisponibles = [actual, actual - 1, actual - 2];
    this.cargarTodo();
  }

  cambiarAnio(anio: number): void {
    this.anioSeleccionado = Number(anio);
    this.cargarTodo();
  }

  cargarTodo(): void {
    this.cargarCitasPorMes();
    this.cargarIngresosPorMes();
    this.cargarCitasPorEstado();
    this.cargarCitasPorEspecialidad();
    this.cargarTopMedicos();
  }

  cargarCitasPorMes(): void {
    this.reporteService.citasPorMes(this.anioSeleccionado).subscribe({
      next: (data) => {
        this.citasPorMesData = {
          ...this.citasPorMesData,
          labels: data.map((d) => d.mes),
          datasets: [{ ...this.citasPorMesData.datasets[0], data: data.map((d) => d.total) }],
        };
        this.totalCitasAnio = data.reduce((acc, d) => acc + d.total, 0);
      },
      error: (err) => console.log(err),
    });
  }

  cargarIngresosPorMes(): void {
    this.reporteService.ingresosPorMes(this.anioSeleccionado).subscribe({
      next: (data) => {
        this.ingresosPorMesData = {
          ...this.ingresosPorMesData,
          labels: data.map((d) => d.mes),
          datasets: [{ ...this.ingresosPorMesData.datasets[0], data: data.map((d) => d.total) }],
        };
        this.totalIngresosAnio = data.reduce((acc, d) => acc + d.total, 0);
      },
      error: (err) => console.log(err),
    });
  }

  cargarCitasPorEstado(): void {
    this.reporteService.citasPorEstado(this.anioSeleccionado).subscribe({
      next: (data) => {
        this.citasPorEstadoData = {
          ...this.citasPorEstadoData,
          labels: data.map((d) => this.formatearEstado(d.estado)),
          datasets: [{ ...this.citasPorEstadoData.datasets[0], data: data.map((d) => d.total) }],
        };
      },
      error: (err) => console.log(err),
    });
  }

  cargarCitasPorEspecialidad(): void {
    this.reporteService.citasPorEspecialidad(this.anioSeleccionado).subscribe({
      next: (data) => {
        this.citasPorEspecialidadData = {
          ...this.citasPorEspecialidadData,
          labels: data.map((d) => d.especialidad),
          datasets: [{ ...this.citasPorEspecialidadData.datasets[0], data: data.map((d) => d.total) }],
        };
      },
      error: (err) => console.log(err),
    });
  }

  cargarTopMedicos(): void {
    this.reporteService.topMedicos(this.anioSeleccionado).subscribe({
      next: (data) => (this.topMedicos = data),
      error: (err) => console.log(err),
    });
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