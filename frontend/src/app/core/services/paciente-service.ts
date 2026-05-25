import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Paciente, PacienteDTO, PageResponse } from '../../model/Paciente';

@Injectable({
  providedIn: 'root'
})
export class PacienteService {
  private api = 'http://localhost:8080/api/paciente';

  constructor(private http: HttpClient) {}

  listar(page?: number, size?: number): Observable<PageResponse<Paciente>> {
    if (page != null && size != null) {
      return this.http.get<PageResponse<Paciente>>(`${this.api}?page=${page}&size=${size}`);
    }
    // when no pagination requested, return a wrapper with content array
    return this.http.get<PageResponse<Paciente>>(`${this.api}` as any);
  }

  listarTodos(): Observable<Paciente[]> {
    return this.http.get<Paciente[]>(this.api);
  }

  obtener(id: number) {
    return this.http.get<Paciente>(`${this.api}/${id}`);
  }

  registrar(dto: PacienteDTO) {
    return this.http.post(this.api, dto, { responseType: 'text' });
  }

  modificar(id: number, dto: PacienteDTO) {
    return this.http.put(`${this.api}/${id}`, dto);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.api}/${id}`, { responseType: 'text' });
  }

  // toggle active/inactive by calling eliminar for soft-delete, or a dedicated endpoint later
  toggleActivo(id: number) {
    return this.http.patch(`${this.api}/${id}/estado`, {});
  }
}
