package com.app.CitaMed.DTO;
import com.app.CitaMed.Enums.EstadoPago;
import com.app.CitaMed.Enums.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UltimoPagoDTO {
    private String paciente;
    private MetodoPago metodo;
    private Long citaId;
    private Double monto;
    private EstadoPago estado;
}
