package com.app.CitaMed.Model.Administrativo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Consultorios")

public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número del consultorio es obligatorio")
    private String numero;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
    private boolean disponible;

    @NotNull(message = "Debe seleccionar un área")
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;
}
