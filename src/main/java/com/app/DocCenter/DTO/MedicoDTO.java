package com.app.DocCenter.DTO;
import lombok.Data;
import java.time.LocalDate;

@Data

public class MedicoDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;
    private LocalDate fechaNacimiento;
    private String genero;
    private String numeroColegiatura;
    private Long especialidadId;
    private String userName;
    private String password;
}
