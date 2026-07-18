package com.app.CitaMed.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TestimonioPublicoDTO {
    private String nombrePaciente;
    private Integer calificacion;
    private String mensaje;
    private LocalDateTime fechaCreacion;
}