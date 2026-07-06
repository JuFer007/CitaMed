package com.app.CitaMed.DTO;
import com.app.CitaMed.Enums.EstadoPago;
import com.app.CitaMed.Enums.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PagoDetalleDTO {
    private Long id;
    private String paciente;
    private String dni;
    private LocalDateTime fechaHora;
    private Long citaId;
    private String medico;
    private String especialidad;
    private MetodoPago metodoPago;
    private Double monto;
    private EstadoPago estado;
}
