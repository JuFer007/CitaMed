import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Empleado, EmpleadoDTO } from '../../model/Empleado';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class EmpleadoService {
  private apiUrl = `${environment.apiUrl}/api/empleado`;

  constructor(private http: HttpClient) {}

  obtenerTodas(search?: string): Observable<Empleado[]> {
    let params = new HttpParams();
    if (search) params = params.set('search', search);
    return this.http.get<Empleado[]>(this.apiUrl, { params });
  }

  obtenerPorId(id: number): Observable<Empleado> {
    return this.http.get<Empleado>(`${this.apiUrl}/${id}`);
  }

  crear(data: EmpleadoDTO): Observable<string> {
    return this.http.post(this.apiUrl, data, { responseType: 'text' });
  }

  actualizar(id: number, data: EmpleadoDTO): Observable<string> {
    return this.http.put(`${this.apiUrl}/${id}`, data, { responseType: 'text' });
  }

  cambiarEstado(id: number): Observable<string> {
    return this.http.patch(`${this.apiUrl}/${id}/estado`, {}, { responseType: 'text' });
  }
}
