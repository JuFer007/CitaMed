package com.app.DocCenter.Repository.Paciente;
import com.app.DocCenter.Model.Paciente.ConsultaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ConsultaMedicaRepository extends JpaRepository<ConsultaMedica, Long> {
}
