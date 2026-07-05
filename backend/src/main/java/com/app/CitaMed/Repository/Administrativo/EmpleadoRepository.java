package com.app.CitaMed.Repository.Administrativo;
import com.app.CitaMed.Model.Administrativo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    boolean existsByDni(String dni);
    List<Empleado> findByActivoTrue();

    @Query("SELECT e FROM Empleado e WHERE " +
           "LOWER(COALESCE(e.nombre,'')) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(COALESCE(e.apellidoPaterno,'')) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(COALESCE(e.apellidoMaterno,'')) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(COALESCE(e.dni,'')) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(COALESCE(e.email,'')) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(COALESCE(e.telefono,'')) LIKE LOWER(CONCAT('%', :termino, '%'))")
    List<Empleado> buscar(@Param("termino") String termino);
}
