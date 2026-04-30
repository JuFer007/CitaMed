import { Especialidad } from "./Especialidad";

export interface Medico {
  id: number;
  nombre: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
  genero: string;
  numeroColegiatura: string;
  especialidad: Especialidad;
}
