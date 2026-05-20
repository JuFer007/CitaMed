import { Especialidad } from './Especialidad';

export interface Medico {
  id?: number;
  nombre: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
  dni: string;
  telefono: string;
  direccion: string;
  email: string;
  fechaNacimiento: string;
  genero: string;
  numeroColegiatura: string;
  userName: string;
  activo: boolean;
  especialidad: Especialidad;
}

export interface MedicoDTO {
  nombre: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
  dni: string;
  telefono: string;
  direccion: string;
  email: string;
  fechaNacimiento: string;
  genero: string;
  numeroColegiatura: string;
  especialidadId: number;
  userName: string;
  password: string;
}