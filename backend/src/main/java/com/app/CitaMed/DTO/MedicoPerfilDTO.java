package com.app.CitaMed.DTO;
import com.app.CitaMed.Enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class MedicoPerfilDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String email;
    private Genero genero;
    private String numeroColegiatura;
    private String especialidad;
}
