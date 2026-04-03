package com.app.DocCenter.DTO;
import lombok.Data;
import java.time.LocalDateTime;

@Data

public class CitaDTO {
    private Long pacienteId;
    private Long medicoId;
    private Long consultorioId;
    private LocalDateTime fechaHora;
    private String motivoConsulta;
}
