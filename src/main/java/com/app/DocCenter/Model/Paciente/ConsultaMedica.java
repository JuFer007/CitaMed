package com.app.DocCenter.Model.Paciente;
import com.app.DocCenter.Model.Medico.Medico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class ConsultaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private HistorialMedico historial;
    private String motivoConsulta;
    private String observaciones;
}
