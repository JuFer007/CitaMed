export interface Consultorio {
  id: number;
  numero: string;
  descripcion: string;
  disponible: boolean;
  cupoMaximo: number;
  especialidad: {
    id: number;
    nombre: string;
    descripcion: string;
    activo: boolean;
  };
}

export interface ConsultorioPayload {
  numero: string;
  descripcion: string;
  especialidadId: number;
  cupoMaximo?: number;
}
