package com.app.CitaMed.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class PagoEstadoDTO {
    private Long id;
    private Double monto;
    private String metodoPago;
    private String estado;
    private String fechaPago;
}
