package com.app.DocCenter.Model.Agenda;
import com.app.DocCenter.Enums.EstadoCita;
import com.app.DocCenter.Model.Administrativo.Consultorio;
import com.app.DocCenter.Model.Administrativo.Pago;
import com.app.DocCenter.Model.Medico.Medico;
import com.app.DocCenter.Model.Paciente.ConsultaMedica;
import com.app.DocCenter.Model.Paciente.Paciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "Citas")

public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Debe seleccionar un paciente")
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @NotNull(message = "Debe seleccionar un médico")
    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @NotNull(message = "Debe seleccionar un consultorio")
    @ManyToOne
    @JoinColumn(name = "consultorio_id", nullable = false)
    private Consultorio consultorio;

    @NotNull(message = "La fecha y hora de la cita es obligatoria")
    @Future(message = "La cita debe programarse en una fecha futura")
    private LocalDateTime fechaHora;

    @NotBlank(message = "Debe indicar el motivo de la consulta")
    @Size(max = 200, message = "El motivo no puede superar los 200 caracteres")
    private String motivoConsulta;

    @NotNull(message = "Debe indicar el estado de la cita")
    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private Pago pago;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private ConsultaMedica consultaMedica;
}
