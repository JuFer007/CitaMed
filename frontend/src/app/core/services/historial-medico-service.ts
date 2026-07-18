import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HistorialMedicoDetalle } from '../../model/HistorialMedico';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HistorialMedicoService {
  private api = `${environment.apiUrl}/api/historialMedico`;
  private pdfApi = `${environment.apiUrl}/api/pdf`;

  constructor(private http: HttpClient) {}

  obtenerHistorialCompleto(pacienteId: number): Observable<HistorialMedicoDetalle> {
    return this.http.get<HistorialMedicoDetalle>(`${this.api}/paciente/${pacienteId}/completo`);
  }

  descargarHistorial(data: any): Observable<Blob> {
    return this.http.post(`${this.pdfApi}/historial`, data, { responseType: 'blob' });
  }
}
