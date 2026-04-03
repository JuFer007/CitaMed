package com.app.CitaMed.Repository.Paciente;
import com.app.CitaMed.Model.Paciente.ConsultaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository

public interface ConsultaMedicaRepository extends JpaRepository<ConsultaMedica, Long> {
    boolean existsByCitaId(Long citaId);
    List<ConsultaMedica> findByHistorialPacienteId(Long pacienteId);
    Optional<ConsultaMedica> findByCitaId(Long citaId);
}
