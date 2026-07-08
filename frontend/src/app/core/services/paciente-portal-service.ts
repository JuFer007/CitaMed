import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface PortalPerfil {
  id: number;
  nombre: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
  dni: string;
  telefono: string;
  direccion: string;
  email: string;
  fechaNacimiento: string;
  genero: string;
  grupoSanguineo: string;
}

export interface PortalCita {
  id: number;
  medico: string;
  especialidad: string;
  fecha: string;
  hora: string;
  consultorio: string;
  estado: string;
}

export interface PortalDiagnostico {
  id: number;
  citaId: number;
  diagnostico: string;
  receta: string;
  fecha: string;
  medico: string;
}

export interface PortalHistorial {
  fecha: string;
  titulo: string;
  descripcion: string;
  medico: string;
  tipo: string;
}

export interface PortalNotificacion {
  id: number;
  mensaje: string;
  tipo: string;
  leido: boolean;
  fechaCreacion: string;
  referenciaId: number | null;
  tabDestino: string;
}

export interface PortalPago {
  id: number;
  concepto: string;
  medico: string;
  especialidad: string;
  fecha: string;
  monto: number;
  estado: string;
}

@Injectable({ providedIn: 'root' })
export class PacientePortalService {
  private api = 'http://localhost:8080/api/portal';

  constructor(private http: HttpClient) {}

  obtenerPerfil(): Observable<PortalPerfil> {
    return this.http.get<PortalPerfil>(`${this.api}/perfil`);
  }

  actualizarPerfil(dto: any): Observable<any> {
    return this.http.put(`${this.api}/perfil`, dto);
  }

  obtenerProximasCitas(): Observable<PortalCita[]> {
    return this.http.get<PortalCita[]>(`${this.api}/citas`);
  }

  obtenerHistorialCitas(): Observable<PortalCita[]> {
    return this.http.get<PortalCita[]>(`${this.api}/citas/historial`);
  }

  obtenerDetalleCita(id: number): Observable<PortalCita> {
    return this.http.get<PortalCita>(`${this.api}/citas/${id}`);
  }

  cancelarCita(id: number): Observable<any> {
    return this.http.patch(`${this.api}/citas/${id}/cancelar`, {});
  }

  obtenerDiagnostico(citaId: number): Observable<PortalDiagnostico> {
    return this.http.get<PortalDiagnostico>(`${this.api}/diagnosticos/${citaId}`);
  }

  obtenerHistorialClinico(): Observable<PortalHistorial[]> {
    return this.http.get<PortalHistorial[]>(`${this.api}/historial-clinico`);
  }

  obtenerPagos(): Observable<PortalPago[]> {
    return this.http.get<PortalPago[]>(`${this.api}/pagos`);
  }

  obtenerNotificaciones(): Observable<PortalNotificacion[]> {
    return this.http.get<PortalNotificacion[]>(`${this.api}/notificaciones`);
  }

  contarNotificacionesNoLeidas(): Observable<{ cantidad: number }> {
    return this.http.get<{ cantidad: number }>(`${this.api}/notificaciones/no-leidas`);
  }

  marcarNotificacionLeida(id: number): Observable<any> {
    return this.http.patch(`${this.api}/notificaciones/${id}/leer`, {});
  }
}
