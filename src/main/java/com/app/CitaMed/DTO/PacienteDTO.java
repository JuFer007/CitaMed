package com.app.DocCenter.DTO;
import com.app.DocCenter.Enums.Genero;
import com.app.DocCenter.Enums.GrupoSanguineo;
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
    private LocalDate fechaNacimiento;
    private Genero genero;
    private GrupoSanguineo grupoSanguineo;

}
