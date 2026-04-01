package com.app.DocCenter.Model.Medico;
import com.app.DocCenter.Model.Agenda.HorarioMedico;
import com.app.DocCenter.Model.Personas.Persona;
import com.app.DocCenter.Model.Personas.Usuario;
import jakarta.persistence.*;
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

public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Persona persona;

    private String numeroColegiatura;
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = false)
    private Especialidad especialidad;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<HorarioMedico> horarios;
}
