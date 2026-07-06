package com.app.CitaMed.Repository.Administrativo;
import com.app.CitaMed.Model.Administrativo.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    boolean existsByNumero(String numero);
    List<Consultorio> findByDisponibleTrue();

    @Query(value = """
        SELECT c.* FROM consultorios c
        LEFT JOIN medicos m ON m.consultorio_id = c.id
        WHERE c.especialidad_id = :especialidadId AND c.disponible = true
        GROUP BY c.id
        HAVING COUNT(m.id) < 2
    """, nativeQuery = true)
    List<Consultorio> findDisponiblesParaMedico(@Param("especialidadId") Long especialidadId);
}
