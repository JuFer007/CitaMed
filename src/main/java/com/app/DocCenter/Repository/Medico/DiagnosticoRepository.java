package com.app.DocCenter.Repository.Medico;
import com.app.DocCenter.Model.Medico.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
}
