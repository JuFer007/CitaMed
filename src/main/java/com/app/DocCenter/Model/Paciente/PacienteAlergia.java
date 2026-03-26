package com.app.DocCenter.Model.Paciente;
import com.app.DocCenter.Enums.GravedadAlergia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class PacienteAlergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Alergia alergia;

    private String reaccion;

    @Enumerated(EnumType.STRING)
    private GravedadAlergia gravedad;
}
