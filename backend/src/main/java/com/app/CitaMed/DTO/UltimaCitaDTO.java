package com.app.CitaMed.DTO;
import com.app.CitaMed.Enums.EstadoCita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UltimaCitaDTO {
    private String paciente;
    private String medico;
    private String especialidad;
    private LocalDateTime fecha;
    private EstadoCita estado;
}
