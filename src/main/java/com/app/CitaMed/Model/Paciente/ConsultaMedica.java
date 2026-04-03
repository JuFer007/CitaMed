package com.app.DocCenter.Model.Paciente;
import com.app.DocCenter.Model.Agenda.Cita;
import com.app.DocCenter.Model.Medico.Diagnostico;
import com.app.DocCenter.Model.Medico.Tratamiento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "ConsultasMedicas")

public class ConsultaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Debe existir una cita asociada")
    @OneToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    @NotNull(message = "Debe existir un historial médico")
    @ManyToOne
    @JoinColumn(name = "historial_id", nullable = false)
    private HistorialMedico historial;

    @Size(max = 500, message = "Las observaciones no pueden superar los 500 caracteres")
    private String observaciones;

    @Positive(message = "El peso debe ser mayor que 0")
    private Double peso;

    @Positive(message = "La talla debe ser mayor que 0")
    private Double talla;

    @Pattern(regexp = "^\\d{2,3}/\\d{2,3}$", message = "La presión arterial debe tener formato 120/80")
    private String presionArterial;

    @Pattern(regexp = "^\\d{2}(\\.\\d)?$", message = "La temperatura debe tener formato válido (ej: 36.5)")
    private String temperatura;

    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<Diagnostico> diagnosticos;

    @OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL)
    private Tratamiento tratamiento;
}
