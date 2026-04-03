package com.app.DocCenter.Repository.Administrativo;
import com.app.DocCenter.Model.Administrativo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    boolean existsByDni(String dni);
    List<Empleado> findByActivoTrue();
}
