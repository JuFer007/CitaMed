import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class PortalService {
  private apiUrl = `${environment.apiUrl}/api`;

  constructor(private http: HttpClient) {}

  registrar(dto: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/portal/registro`, dto);
  }

  consultarReniec(dni: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/reniec/dni/${dni}`);
  }

  recuperarPassword(email: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/portal/recuperar-password`, { email });
  }

  restablecerPassword(token: string, nuevaPassword: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/portal/restablecer-password`, { token, nuevaPassword });
  }
}
