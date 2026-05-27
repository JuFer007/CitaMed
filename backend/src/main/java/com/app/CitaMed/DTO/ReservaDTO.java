package com.app.CitaMed.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaDTO {
    private String dni;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String email;
    private String direccion;
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;
    private String genero;
    private String grupoSanguineo;
    private Long medicoId;
    private Long consultorioId;
    private String fechaHora;
    private String motivoConsulta;
}
