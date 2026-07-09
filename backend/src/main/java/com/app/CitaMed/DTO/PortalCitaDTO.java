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
    private String fecha;
    private String hora;
    private String medicoNombre;
    private String medicoApellido;
    private String medico;
    private String especialidad;
    private String consultorio;
    private String estado;
    private String motivoConsulta;
    private DiagnosticoDTO diagnostico;
    private PagoEstadoDTO pago;
}
