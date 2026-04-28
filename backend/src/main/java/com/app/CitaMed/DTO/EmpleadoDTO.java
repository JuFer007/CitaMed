package com.app.CitaMed.DTO;
import com.app.CitaMed.Enums.Genero;
import com.app.CitaMed.Enums.Rol;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EmpleadoDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;
    private LocalDate fechaNacimiento;
    private Genero genero;
    private double salario;
    private LocalDate fechaIngreso;
    private String userName;
    private String password;
    private Rol rol;
}
