package com.app.DocCenter.Model.Paciente;
import com.app.DocCenter.Model.Agenda.Cita;
import com.app.DocCenter.Model.Medico.Diagnostico;
import com.app.DocCenter.Model.Medico.Medico;
import com.app.DocCenter.Model.Medico.Tratamiento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class ConsultaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    @ManyToOne
    @JoinColumn(name = "historial_id", nullable = false)
    private HistorialMedico historial;

    private String observaciones;
    private Double peso;
    private Double talla;
    private String presionArterial;
    private String temperatura;

    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<Diagnostico> diagnosticos;

    @OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL)
    private Tratamiento tratamiento;
}
