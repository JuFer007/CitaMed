package com.app.CitaMed.Model.Administrativo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consultas")

public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ingresar un email válido")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "El mensaje es obligatorio")
    @Size(max = 1000, message = "El mensaje no puede superar los 1000 caracteres")
    private String mensaje;

    private LocalDateTime fechaEnvio;

    private boolean leido;

    private boolean respondido;

    @Size(max = 2000)
    private String respuesta;

    private LocalDateTime fechaRespuesta;

    @Size(max = 100)
    private String respondidoPor;

    @PrePersist
    public void prePersist() {
        this.fechaEnvio = LocalDateTime.now();
        this.leido = false;
        this.respondido = false;
    }
}
