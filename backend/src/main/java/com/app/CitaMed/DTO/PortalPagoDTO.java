package com.app.CitaMed.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PortalPagoDTO {
    private Long id;
    private Long citaId;
    private String concepto;
    private String medico;
    private String especialidad;
    private String fecha;
    private String hora;
    private Double monto;
    private String metodoPago;
    private String estado;
    private String dniPaciente;
    private String pacienteNombre;
}
