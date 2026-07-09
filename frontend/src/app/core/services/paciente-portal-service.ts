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
  diagnostico?: PortalDiagnostico;
  pago?: PortalPagoEstado;
}

export interface PortalDiagnostico {
  citaId: number;
  enfermedad: string;
  descripcion: string;
  receta: string;
  indicaciones: string;
}

export interface PortalPagoEstado {
  id: number;
  monto: number;
  metodoPago: string;
  estado: string;
  fechaPago: string;
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
  citaId: number;
  concepto: string;
  medico: string;
  especialidad: string;
  fecha: string;
  hora: string;
  monto: number;
  metodoPago: string;
  estado: string;
  dniPaciente: string;
  pacienteNombre: string;
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

  obtenerHistorialClinico(): Observable<any> {
    return this.http.get<any>(`${this.api}/historial-clinico`);
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

  marcarTodasNotificacionesLeidas(): Observable<any> {
    return this.http.patch(`${this.api}/notificaciones/leer-todas`, {});
  }

  descargarRecetaPdf(citaId: number): Observable<Blob> {
    return this.http.get(`http://localhost:8080/api/pdf/receta/cita/${citaId}`, { responseType: 'blob' });
  }

  descargarTicketPdf(data: any): Observable<Blob> {
    return this.http.post(`http://localhost:8080/api/pdf/ticket`, data, { responseType: 'blob' });
  }

  descargarHistorialPdf(data: any): Observable<Blob> {
    return this.http.post(`http://localhost:8080/api/pdf/historial`, data, { responseType: 'blob' });
  }

  obtenerEspecialidades(): Observable<any[]> {
    return this.http.get<any[]>(`${this.api}/especialidades`);
  }

  obtenerMedicos(especialidadId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.api}/medicos?especialidadId=${especialidadId}`);
  }

  obtenerSlots(especialidadId: number, fecha: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.api}/slots?especialidadId=${especialidadId}&fecha=${fecha}`);
  }

  reservarCita(data: any): Observable<any> {
    return this.http.post(`${this.api}/citas/reservar`, data);
  }
}
