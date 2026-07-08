package com.app.CitaMed.DTO;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class PerfilRequest {
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
    private String telefono;

    @Size(max = 150)
    private String direccion;

    @Email(message = "Debe ingresar un correo válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;
}
