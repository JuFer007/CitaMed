package com.app.DocCenter.Repository.Administrativo;
import com.app.DocCenter.Model.Administrativo.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface PagoRepository extends JpaRepository<Pago, Long> {
    boolean existsByCitaId(Long citaId);
    Optional<Pago> findByCitaId(Long citaId);
}
