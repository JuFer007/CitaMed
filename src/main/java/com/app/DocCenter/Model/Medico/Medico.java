package com.app.DocCenter.Model.Medico;
import com.app.DocCenter.Model.Administrativo.Empleado;
import com.app.DocCenter.Model.Agenda.HorarioMedico;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

public class Medico extends Empleado {
    @ManyToOne
    private Especialidad especialidad;

    @OneToMany(mappedBy = "medico")
    private List<HorarioMedico> horarios;
}
