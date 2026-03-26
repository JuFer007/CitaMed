package com.app.DocCenter.Model.Paciente;
import com.app.DocCenter.Enums.GrupoSanguineo;
import com.app.DocCenter.Model.Personas.Persona;
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

public class Paciente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private GrupoSanguineo grupoSanguineo;

    @OneToMany(mappedBy = "paciente")
    private List<PacienteAlergia> alergias;
}
