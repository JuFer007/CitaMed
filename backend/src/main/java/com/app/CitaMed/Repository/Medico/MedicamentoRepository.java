package com.app.CitaMed.Repository.Medico;
import com.app.CitaMed.Model.Medico.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByTratamientoId(Long tratamientoId);
}
