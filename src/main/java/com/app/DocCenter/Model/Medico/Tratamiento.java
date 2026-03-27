package com.app.DocCenter.Model.Medico;
import com.app.DocCenter.Model.Paciente.ConsultaMedica;
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

public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String indicaciones;

    @OneToOne
    private ConsultaMedica consulta;

    @OneToMany(mappedBy = "tratamiento")
    private List<Medicamento> medicamentos;
}
