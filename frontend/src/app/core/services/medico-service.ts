import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Medico, MedicoDTO } from '../../model/Medico';
import { Especialidad } from '../../model/Especialidad';

@Injectable({
  providedIn: 'root'
})
export class MedicoService {

  private api = 'http://localhost:8080/api/medico';
  private apiEspecialidad = 'http://localhost:8080/api/especialidad';

  constructor(private http: HttpClient) {}

  listar(): Observable<Medico[]> {
    return this.http.get<Medico[]>(this.api);
  }

  registrar(dto: MedicoDTO): Observable<Medico> {
    return this.http.post<Medico>(this.api, dto);
  }

  modificar(id: number, dto: MedicoDTO): Observable<any> {
    return this.http.put(`${this.api}/${id}`, dto);
  }

  cambiarEstado(id: number): Observable<any> {
    return this.http.patch(`${this.api}/${id}/estado`, {});
  }

  listarEspecialidades(): Observable<Especialidad[]> {
    return this.http.get<Especialidad[]>(this.apiEspecialidad);
  }

  subirFoto(id: number, archivo: File): Observable<any> {
    const formData = new FormData();
    formData.append('archivo', archivo);
    return this.http.post(`${this.api}/${id}/foto`, formData);
  }
}
