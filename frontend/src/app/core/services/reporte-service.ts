import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface ReportFilter {
  anio?: number | null;
  mes?: number | null;
  fechaInicio?: string | null;
  fechaFin?: string | null;
}

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
  private apiUrl = `${environment.apiUrl}/api/reportes`;

  constructor(private http: HttpClient) {}

  private buildParams(filter: ReportFilter): HttpParams {
    let params = new HttpParams();
    if (filter.anio != null) params = params.set('anio', filter.anio);
    if (filter.mes != null) params = params.set('mes', filter.mes);
    if (filter.fechaInicio) params = params.set('fechaInicio', filter.fechaInicio);
    if (filter.fechaFin) params = params.set('fechaFin', filter.fechaFin);
    return params;
  }

  citasPorMes(filter: ReportFilter): Observable<ReporteCitasMes[]> {
    return this.http.get<ReporteCitasMes[]>(`${this.apiUrl}/citas-por-mes`, { params: this.buildParams(filter) });
  }

  ingresosPorMes(filter: ReportFilter): Observable<ReporteIngresoMes[]> {
    return this.http.get<ReporteIngresoMes[]>(`${this.apiUrl}/ingresos-por-mes`, { params: this.buildParams(filter) });
  }

  citasPorEstado(filter: ReportFilter): Observable<ReporteEstado[]> {
    return this.http.get<ReporteEstado[]>(`${this.apiUrl}/citas-por-estado`, { params: this.buildParams(filter) });
  }

  citasPorEspecialidad(filter: ReportFilter): Observable<ReporteEspecialidad[]> {
    return this.http.get<ReporteEspecialidad[]>(`${this.apiUrl}/citas-por-especialidad`, { params: this.buildParams(filter) });
  }

  topMedicos(filter: ReportFilter): Observable<ReporteMedico[]> {
    return this.http.get<ReporteMedico[]>(`${this.apiUrl}/top-medicos`, { params: this.buildParams(filter) });
  }
}