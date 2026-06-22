package com.app.CitaMed.DTO;

import com.app.CitaMed.Enums.Genero;
import com.app.CitaMed.Enums.GrupoSanguineo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DniLookupDTO {
    private String dni;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String email;
    private String direccion;
    private LocalDate fechaNacimiento;
    private Genero genero;
    private GrupoSanguineo grupoSanguineo;
    private boolean existeLocal;
}
