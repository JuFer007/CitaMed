export interface Empleado {
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
  salario: number;
  fechaIngreso: string;
  activo: boolean;
  usuario: UsuarioRef;
}

export interface UsuarioRef {
  id?: number;
  userName: string;
  rol: string;
}

export interface EmpleadoDTO {
  nombre: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
  dni: string;
  telefono: string;
  direccion: string;
  email: string;
  fechaNacimiento: string;
  genero: string;
  salario: number;
  userName: string;
  password: string;
  rol: string;
}
