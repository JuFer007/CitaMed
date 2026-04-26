package com.app.CitaMed.Repository.Paciente;
import com.app.CitaMed.Model.Paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByDni(String dni);
    Paciente findByDni(String dni);
}
