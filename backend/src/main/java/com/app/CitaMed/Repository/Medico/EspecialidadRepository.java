package com.app.CitaMed.Repository.Medico;
import com.app.CitaMed.Model.Medico.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
}
