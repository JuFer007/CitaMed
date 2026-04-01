package com.app.DocCenter.Model.Agenda;
import com.app.DocCenter.Enums.EstadoCita;
import com.app.DocCenter.Model.Administrativo.Consultorio;
import com.app.DocCenter.Model.Medico.Medico;
import com.app.DocCenter.Model.Paciente.Paciente;
import jakarta.persistence.*;
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

public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Consultorio consultorio;
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;
}
