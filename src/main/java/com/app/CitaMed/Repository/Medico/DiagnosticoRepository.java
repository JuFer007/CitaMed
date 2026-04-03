package com.app.DocCenter.Repository.Medico;
import com.app.DocCenter.Model.Medico.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
    List<Diagnostico> findByConsultaId(Long consultaId);
}
