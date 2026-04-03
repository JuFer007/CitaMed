package com.app.DocCenter.Model.Medico;
import com.app.DocCenter.Model.Paciente.ConsultaMedica;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tratamientos")

public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Las indicaciones del tratamiento son obligatorias")
    @Size(max = 500, message = "Las indicaciones no pueden superar los 500 caracteres")
    private String indicaciones;

    @Positive(message = "La duración del tratamiento debe ser mayor que 0 días")
    private int duracionDias;

    @NotNull(message = "Debe existir una consulta médica asociada")
    @OneToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private ConsultaMedica consulta;

    @OneToMany(mappedBy = "tratamiento", cascade = CascadeType.ALL)
    private List<Medicamento> medicamentos;
}
