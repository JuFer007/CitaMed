export interface Testimonio {
  id: number;
  calificacion: number;
  mensaje: string;
  fechaCreacion: string;
}

export interface TestimonioPublico {
  nombrePaciente: string;
  calificacion: number;
  mensaje: string;
  fechaCreacion: string;
}
