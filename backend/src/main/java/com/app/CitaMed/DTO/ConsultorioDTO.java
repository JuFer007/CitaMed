package com.app.CitaMed.DTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class ConsultorioDTO {
    @NotBlank(message = "El número del consultorio es obligatorio")
    private String numero;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "Debe seleccionar un área")
    private Long especialidadId;

    @Min(value = 1, message = "El cupo mínimo es 1 médico")
    @Max(value = 10, message = "El cupo máximo es 10 médicos")
    private Integer cupoMaximo = 3;
}
