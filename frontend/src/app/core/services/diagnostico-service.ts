import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Diagnostico, DiagnosticoDTO } from '../../model/Diagnostico';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class DiagnosticoService {
  private apiUrl = `${environment.apiUrl}/api/diagnostico`;
  private pdfApi = `${environment.apiUrl}/api/pdf`;

  constructor(private http: HttpClient) {}

  atender(dto: DiagnosticoDTO): Observable<string> {
    return this.http.post(`${this.apiUrl}/atender`, dto, { responseType: 'text' });
  }

  obtenerPorCita(citaId: number): Observable<Diagnostico> {
    return this.http.get<Diagnostico>(`${this.apiUrl}/cita/${citaId}`);
  }

  actualizar(citaId: number, dto: DiagnosticoDTO): Observable<string> {
    return this.http.put(`${this.apiUrl}/cita/${citaId}`, dto, { responseType: 'text' });
  }

  descargarReceta(citaId: number): Observable<Blob> {
    return this.http.get(`${this.pdfApi}/receta/cita/${citaId}`, {
      responseType: 'blob',
    });
  }
}
