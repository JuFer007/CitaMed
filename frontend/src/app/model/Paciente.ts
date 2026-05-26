export interface Paciente {
  id?: number;
  nombre: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
  dni: string;
  telefono: string;
  direccion?: string;
  email?: string;
  fechaNacimiento?: string;
  genero?: string;
  grupoSanguineo?: string;
  activo?: boolean;
}

export interface PacienteDTO {
  nombre: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
  dni: string;
  telefono: string;
  direccion?: string;
  email?: string;
  fechaNacimiento?: string;
  genero?: string;
  grupoSanguineo?: string;
}

export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  number: number;
  size: number;
}
