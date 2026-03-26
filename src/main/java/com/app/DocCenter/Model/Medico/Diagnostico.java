package com.app.DocCenter.Model.Medico;
import com.app.DocCenter.Model.Paciente.ConsultaMedica;
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

public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enfermedad;
    private String descripcion;

    @ManyToOne
    private ConsultaMedica consulta;
}
