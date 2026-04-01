package com.app.DocCenter.Repository.Agenda;
import com.app.DocCenter.Model.Agenda.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CitaRepository extends JpaRepository<Cita, Long> {
}
