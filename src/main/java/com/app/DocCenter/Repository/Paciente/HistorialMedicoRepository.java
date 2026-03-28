package com.app.DocCenter.Repository.Paciente;
import com.app.DocCenter.Model.Paciente.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HistorialMedicoRepository extends JpaRepository<Long, HistorialMedico> {
}
