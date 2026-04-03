package com.app.DocCenter.Repository.Medico;
import com.app.DocCenter.Model.Medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    boolean existsByDni(String dni);
    List<Medico> findByEspecialidadIdAndActivoTrue(Long especialidadId);
}
