package com.app.DocCenter.Repository.Agenda;
import com.app.DocCenter.Model.Agenda.HorarioMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HorarioMedicoRepository extends JpaRepository<HorarioMedico, Long> {
}
