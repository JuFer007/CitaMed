import { EstadoCita } from './Cita';

export interface HistorialMedicoDetalle {
  paciente: PacienteInfo;
  citas: CitaHistorial[];
}

export interface PacienteInfo {
  id: number;
  nombre: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
  dni: string;
  telefono: string;
  email?: string;
  direccion?: string;
  fechaNacimiento?: string;
  genero?: string;
  grupoSanguineo?: string;
}

export interface CitaHistorial {
  citaId: number;
  fechaHora: string;
  medicoNombre: string;
  medicoApellidoPaterno: string;
  medicoApellidoMaterno: string;
  medicoEspecialidad: string;
  consultorioNumero: string;
  motivoConsulta: string;
  estado: EstadoCita;
  diagnostico: DiagnosticoHistorial | null;
}

export interface DiagnosticoHistorial {
  id: number;
  enfermedad: string;
  descripcion?: string;
  receta?: string;
  indicaciones?: string;
}
