package com.app.CitaMed.Model.Administrativo;

import com.app.CitaMed.Enums.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "Usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Pattern(regexp = "^[a-zA-Z0-9._]+$", message = "El username solo puede contener letras, números, punto o guion bajo")
    @Size(min = 4, max = 20, message = "El username debe tener entre 4 y 20 caracteres")
    @Column(unique = true)
    private String userName;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotNull(message = "Debe asignar un rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;

    private boolean activo;
}