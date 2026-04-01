package com.app.DocCenter.DTO;
import lombok.Data;

@Data

public class DiagnosticoDTO {
    private Long consultaId;
    private String codigoCIE10;
    private String enfermedad;
    private String descripcion;
}
