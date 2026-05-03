import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { LoginResponse } from '../../model/Perfil';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient, private router: Router) { }

  login(usuario: string, clave: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, { usuario, clave })
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          localStorage.setItem('nombreUsuario', response.nombre);
          localStorage.setItem('usuario', response.usuario);
          localStorage.setItem('perfil', response.perfil);
        })
      );
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getNombre(): string | null {
    return localStorage.getItem('nombreUsuario');
  }

  getPerfil(): string | null {
    return localStorage.getItem('perfil');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}