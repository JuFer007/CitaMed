import { Medico } from "./Medico ";
import { HorarioMedico } from "./HorarioMedico ";

export interface SlotHorario {
  medico: Medico;
  horario: HorarioMedico;
  horaDisponible: string;
}
