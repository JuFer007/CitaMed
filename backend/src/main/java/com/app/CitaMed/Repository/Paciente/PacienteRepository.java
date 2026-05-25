package com.app.CitaMed.Repository.Paciente;
import com.app.CitaMed.Model.Paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByDni(String dni);
    Paciente findByDniAndActivoTrue(String dni);
    // find by dni regardless of activo (used for reactivations)
    Paciente findByDni(String dni);
    // find active patients
    org.springframework.data.domain.Page<Paciente> findByActivoTrue(org.springframework.data.domain.Pageable pageable);
    java.util.List<Paciente> findByActivoTrue();
}
