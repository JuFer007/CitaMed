package com.app.DocCenter.Repository.Administrativo;
import com.app.DocCenter.Model.Administrativo.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
