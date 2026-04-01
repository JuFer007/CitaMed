package com.app.DocCenter.DTO;
import lombok.Data;

@Data

public class ConsultaMedicaDTO {
    private Long citaId;
    private String observaciones;
    private Double peso;
    private Double talla;
    private String presionArterial;
    private String temperatura;
}
