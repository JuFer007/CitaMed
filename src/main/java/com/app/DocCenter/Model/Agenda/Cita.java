package com.app.DocCenter.Model.Agenda;
import com.app.DocCenter.Enums.EstadoCita;
import com.app.DocCenter.Model.Administrativo.Consultorio;
import com.app.DocCenter.Model.Administrativo.Pago;
import com.app.DocCenter.Model.Medico.Medico;
import com.app.DocCenter.Model.Paciente.ConsultaMedica;
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
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "consultorio_id", nullable = false)
    private Consultorio consultorio;

    private LocalDateTime fechaHora;
    private String motivoConsulta;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private Pago pago;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private ConsultaMedica consultaMedica;
}
