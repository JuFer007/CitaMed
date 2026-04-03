package com.app.CitaMed.Repository.Administrativo;
import com.app.CitaMed.Model.Administrativo.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    boolean existsByNumero(String numero);
    List<Consultorio> findByDisponibleTrue();
    List<Consultorio> findByAreaId(Long areaId);
}
