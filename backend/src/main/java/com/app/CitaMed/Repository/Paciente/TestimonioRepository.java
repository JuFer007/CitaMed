package com.app.CitaMed.Repository.Paciente;
import com.app.CitaMed.Model.Paciente.Testimonio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TestimonioRepository extends JpaRepository<Testimonio, Long> {
    List<Testimonio> findByPacienteIdOrderByFechaCreacionDesc(Long pacienteId);
    List<Testimonio> findByActivoTrueOrderByFechaCreacionDesc();
}
