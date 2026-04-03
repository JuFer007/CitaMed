package com.app.DocCenter.Repository.Medico;
import com.app.DocCenter.Model.Medico.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
    Optional<Tratamiento> findByConsultaId(Long consultaId);
    boolean existsByConsultaId(Long consultaId);
}
