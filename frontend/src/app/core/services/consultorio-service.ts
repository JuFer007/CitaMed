import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Consultorio, ConsultorioPayload } from '../../model/Consultorio';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ConsultorioService {
  private api = `${environment.apiUrl}/api/consultorio`;

  constructor(private http: HttpClient) {}

  listar(): Observable<Consultorio[]> {
    return this.http.get<Consultorio[]>(this.api);
  }

  listarDisponibles(): Observable<Consultorio[]> {
    return this.http.get<Consultorio[]>(`${this.api}/disponibles`);
  }

  disponiblesParaMedico(especialidadId: number): Observable<Consultorio[]> {
    return this.http.get<Consultorio[]>(`${this.api}/disponibles-para-medico`, {
      params: { especialidadId }
    });
  }

  obtenerPorId(id: number): Observable<Consultorio> {
    return this.http.get<Consultorio>(`${this.api}/${id}`);
  }

  crear(dto: ConsultorioPayload): Observable<string> {
    return this.http.post(this.api, dto, { responseType: 'text' });
  }

  actualizar(id: number, dto: ConsultorioPayload): Observable<string> {
    return this.http.put(`${this.api}/${id}`, dto, { responseType: 'text' });
  }

  toggleDisponible(id: number): Observable<string> {
    return this.http.patch(`${this.api}/${id}/disponibilidad`, {}, { responseType: 'text' });
  }
}
