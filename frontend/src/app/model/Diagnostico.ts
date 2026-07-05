export interface DiagnosticoDTO {
  citaId: number;
  enfermedad: string;
  descripcion?: string;
  receta?: string;
  indicaciones?: string;
}

export interface Diagnostico {
  id: number;
  enfermedad: string;
  descripcion: string;
  receta: string;
  indicaciones: string;
  citaId: number;
}
