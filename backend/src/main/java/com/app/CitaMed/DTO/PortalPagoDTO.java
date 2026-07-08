package com.app.CitaMed.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PortalPagoDTO {
    private Long id;
    private String concepto;
    private String medico;
    private String especialidad;
    private String fecha;
    private Double monto;
    private String estado;
}
