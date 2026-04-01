package com.app.DocCenter.DTO;
import lombok.Data;

@Data

public class MedicamentoDTO {
    private Long tratamientoId;
    private String nombre;
    private String dosis;
    private String frecuencia;
    private int duracionDias;
    private String instrucciones;
}
