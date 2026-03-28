package com.app.DocCenter.Repository.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DiagnosticoRepository extends JpaRepository<Long, DiagnosticoRepository> {
}
