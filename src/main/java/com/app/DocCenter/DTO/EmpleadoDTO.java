package com.app.DocCenter.DTO;
import com.app.DocCenter.Enums.Rol;
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
    private String genero;
    private String cargo;
    private double salario;
    private LocalDate fechaIngreso;
    private String userName;
    private String password;
    private Rol rol;
}
