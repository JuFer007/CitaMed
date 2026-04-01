package com.app.DocCenter.Repository.Medico;
import com.app.DocCenter.Model.Medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    boolean existsByPersonaDni(String dni);
    List<Medico> findByEspecialidadIdAndActivoTrue(Long especialidadId);
}
