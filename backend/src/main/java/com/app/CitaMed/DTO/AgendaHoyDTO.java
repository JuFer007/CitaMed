package com.app.CitaMed.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AgendaHoyDTO {
    private String hora;
    private String paciente;
    private String detalle;
}
