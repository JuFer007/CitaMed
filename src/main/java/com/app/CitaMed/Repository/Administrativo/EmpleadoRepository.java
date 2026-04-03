package com.app.CitaMed.Repository.Administrativo;
import com.app.CitaMed.Model.Administrativo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    boolean existsByDni(String dni);
    List<Empleado> findByActivoTrue();
}
