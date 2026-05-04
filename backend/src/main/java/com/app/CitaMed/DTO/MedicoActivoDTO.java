package com.app.CitaMed.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MedicoActivoDTO {
    private String iniciales;
    private String medico;
    private String cmp;
    private String especialidad;
    private Long citas;
}
