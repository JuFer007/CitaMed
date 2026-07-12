package com.app.CitaMed.Repository.Paciente;
import com.app.CitaMed.Model.Paciente.Paciente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    long count();
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
    Paciente findByDniAndActivoTrue(String dni);
    Paciente findByDni(String dni);
    Optional<Paciente> findByEmail(String email);
    Optional<Paciente> findByUsuarioId(Long usuarioId);
    Page<Paciente> findByActivoTrue(Pageable pageable);
    java.util.List<Paciente> findByActivoTrue();

    @Query("SELECT p FROM Paciente p WHERE " +
   "(:incluirInactivos = true OR p.activo = true) AND " +
   "(:termino IS NULL OR :termino = '' OR " +
   "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
   "LOWER(p.apellidoPaterno) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
   "LOWER(p.apellidoMaterno) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
   "p.dni LIKE CONCAT('%', :termino, '%'))")
    Page<Paciente> buscarConFiltros(@Param("termino") String termino,
    @Param("incluirInactivos") boolean incluirInactivos,
    Pageable pageable);

    @Query("SELECT p FROM Paciente p WHERE p.id IN :ids AND " +
   "(:incluirInactivos = true OR p.activo = true) AND " +
   "(:termino IS NULL OR :termino = '' OR " +
   "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
   "LOWER(p.apellidoPaterno) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
   "LOWER(p.apellidoMaterno) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
   "p.dni LIKE CONCAT('%', :termino, '%'))")
    Page<Paciente> buscarPorIds(@Param("ids") List<Long> ids,
    @Param("termino") String termino,
    @Param("incluirInactivos") boolean incluirInactivos,
    Pageable pageable);
}