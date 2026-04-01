package com.app.DocCenter.Repository.Paciente;
import com.app.DocCenter.Model.Paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByPersonaDni(String dni);
    Paciente findByPersonaDni(String dni);
}
