package com.app.CitaMed.Repository.Medico;
import com.app.CitaMed.Model.Medico.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
    List<Diagnostico> findByConsultaId(Long consultaId);
}
