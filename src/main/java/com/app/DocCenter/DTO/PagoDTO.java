package com.app.DocCenter.DTO;
import com.app.DocCenter.Enums.MetodoPago;
import lombok.Data;

@Data

public class PagoDTO {
    private Long citaId;
    private double monto;
    private MetodoPago metodoPago;
    private String comprobante;
}
