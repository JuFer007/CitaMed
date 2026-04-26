package com.app.CitaMed.Repository.Paciente;
import com.app.CitaMed.Model.Paciente.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long> {
    Optional<HistorialMedico> findByPacienteId(Long pacienteId);
}
