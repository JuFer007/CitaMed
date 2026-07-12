package com.app.CitaMed.DTO;
import com.app.CitaMed.Enums.EstadoCita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReporteEstadoDTO {
    private EstadoCita estado;
    private Long total;

}