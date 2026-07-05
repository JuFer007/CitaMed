import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HistorialMedicoDetalle } from '../../model/HistorialMedico';

@Injectable({
  providedIn: 'root'
})
export class HistorialMedicoService {
  private api = 'http://localhost:8080/api/historialMedico';
  private pdfApi = 'http://localhost:8080/api/pdf';

  constructor(private http: HttpClient) {}

  obtenerHistorialCompleto(pacienteId: number): Observable<HistorialMedicoDetalle> {
    return this.http.get<HistorialMedicoDetalle>(`${this.api}/paciente/${pacienteId}/completo`);
  }

  descargarHistorial(data: any): Observable<Blob> {
    return this.http.post(`${this.pdfApi}/historial`, data, { responseType: 'blob' });
  }
}
