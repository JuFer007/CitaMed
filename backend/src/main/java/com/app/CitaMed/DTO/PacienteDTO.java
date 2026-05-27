package com.app.CitaMed.DTO;

import com.app.CitaMed.Enums.Genero;
import com.app.CitaMed.Enums.GrupoSanguineo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;
    private Genero genero;
    private GrupoSanguineo grupoSanguineo;
}
