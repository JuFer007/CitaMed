package com.app.CitaMed.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor

public class PortalCitaDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private String medicoNombre;
    private String medicoApellido;
    private String especialidad;
    private String consultorio;
    private String estado;
    private String motivoConsulta;
    private DiagnosticoDTO diagnostico;
    private PagoEstadoDTO pago;
}
