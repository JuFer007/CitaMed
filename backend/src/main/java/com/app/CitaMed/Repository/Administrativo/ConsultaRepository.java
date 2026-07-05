package com.app.CitaMed.Repository.Administrativo;
import com.app.CitaMed.Model.Administrativo.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findAllByOrderByFechaEnvioDesc();
    long countByLeidoFalse();
}
