import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ReporteCitasMes { mes: string; total: number; }
export interface ReporteIngresoMes { mes: string; total: number; }
export interface ReporteEstado { estado: string; total: number; }
export interface ReporteEspecialidad { especialidad: string; total: number; }
export interface ReporteMedico {
  iniciales: string;
  medico: string;
  cmp: string;
  especialidad: string;
  citas: number;
}

@Injectable({ providedIn: 'root' })
export class ReporteService {
  private apiUrl = 'http://localhost:8080/api/reportes';

  constructor(private http: HttpClient) {}

  citasPorMes(anio: number): Observable<ReporteCitasMes[]> {
    return this.http.get<ReporteCitasMes[]>(`${this.apiUrl}/citas-por-mes`, { params: { anio } });
  }

  ingresosPorMes(anio: number): Observable<ReporteIngresoMes[]> {
    return this.http.get<ReporteIngresoMes[]>(`${this.apiUrl}/ingresos-por-mes`, { params: { anio } });
  }

  citasPorEstado(anio: number): Observable<ReporteEstado[]> {
    return this.http.get<ReporteEstado[]>(`${this.apiUrl}/citas-por-estado`, { params: { anio } });
  }

  citasPorEspecialidad(anio: number): Observable<ReporteEspecialidad[]> {
    return this.http.get<ReporteEspecialidad[]>(`${this.apiUrl}/citas-por-especialidad`, { params: { anio } });
  }

  topMedicos(anio: number): Observable<ReporteMedico[]> {
    return this.http.get<ReporteMedico[]>(`${this.apiUrl}/top-medicos`, { params: { anio } });
  }
}