package com.app.CitaMed.DTO;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PortalReservaRequest {
    @NotNull(message = "Debe seleccionar una especialidad")
    private Long especialidadId;

    @NotNull(message = "Debe seleccionar un médico")
    private Long medicoId;

    @NotNull(message = "Debe seleccionar un consultorio")
    private Long consultorioId;

    @NotBlank(message = "La fecha y hora de la cita es obligatoria")
    private String fechaHora;

    @NotBlank(message = "Debe indicar el motivo de la consulta")
    @Size(max = 200, message = "El motivo no puede superar los 200 caracteres")
    private String motivoConsulta;
}
