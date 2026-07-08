import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PortalService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  registrar(dto: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/portal/registro`, dto);
  }

  consultarReniec(dni: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/reniec/dni/${dni}`);
  }
}
