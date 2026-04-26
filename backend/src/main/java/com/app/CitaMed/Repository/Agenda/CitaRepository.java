package com.app.CitaMed.Repository.Agenda;
import com.app.CitaMed.Enums.EstadoCita;
import com.app.CitaMed.Model.Agenda.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByMedicoId(Long medicoId);
    List<Cita> findByPacienteId(Long pacienteId);
    boolean existsByMedicoIdAndFechaHoraAndEstadoNot(Long medicoId, LocalDateTime fechaHora, EstadoCita estado);
    boolean existsByPacienteIdAndFechaHoraAndEstadoNot(Long pacienteId, LocalDateTime fechaHora, EstadoCita estado);
}
