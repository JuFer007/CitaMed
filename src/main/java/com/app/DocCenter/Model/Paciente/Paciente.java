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

public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Persona persona;

    @Enumerated(EnumType.STRING)
    private GrupoSanguineo grupoSanguineo;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private HistorialMedico historialMedico;
}
