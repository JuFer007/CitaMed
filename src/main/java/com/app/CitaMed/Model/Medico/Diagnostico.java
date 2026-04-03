package com.app.DocCenter.Model.Medico;
import com.app.DocCenter.Model.Paciente.ConsultaMedica;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "Diagnosticos")

public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Debe indicar la enfermedad")
    @Size(max = 100, message = "El nombre de la enfermedad no puede superar los 100 caracteres")
    private String enfermedad;

    @Size(max = 300, message = "La descripción no puede superar los 300 caracteres")
    private String descripcion;

    @NotNull(message = "Debe existir una consulta médica asociada")
    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private ConsultaMedica consulta;
}
