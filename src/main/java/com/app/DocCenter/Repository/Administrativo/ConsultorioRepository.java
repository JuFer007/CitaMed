package com.app.DocCenter.Repository.Administrativo;
import com.app.DocCenter.Model.Administrativo.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
}
