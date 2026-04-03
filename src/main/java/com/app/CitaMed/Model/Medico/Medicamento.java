package com.app.DocCenter.Model.Medico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Medicamentos")

public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del medicamento es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "La dosis es obligatoria")
    @Size(max = 50, message = "La dosis no puede superar los 50 caracteres")
    private String dosis;

    @NotBlank(message = "La frecuencia es obligatoria")
    @Size(max = 50, message = "La frecuencia no puede superar los 50 caracteres")
    private String frecuencia;

    @Positive(message = "La duración debe ser mayor que 0 días")
    private int duracionDias;

    @Size(max = 300, message = "Las instrucciones no pueden superar los 300 caracteres")
    private String instrucciones;

    @NotNull(message = "Debe existir un tratamiento asociado")
    @ManyToOne
    @JoinColumn(name = "tratamiento_id", nullable = false)
    private Tratamiento tratamiento;
}
