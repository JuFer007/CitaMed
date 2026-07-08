package com.app.CitaMed.Repository.Portal;
import com.app.CitaMed.Model.Portal.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByPacienteIdOrderByFechaCreacionDesc(Long pacienteId);
    List<Notificacion> findByPacienteIdAndLeidoFalseOrderByFechaCreacionDesc(Long pacienteId);
    long countByPacienteIdAndLeidoFalse(Long pacienteId);
}
