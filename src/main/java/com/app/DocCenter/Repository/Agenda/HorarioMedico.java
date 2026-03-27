package com.app.DocCenter.Repository.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HorarioMedico extends JpaRepository<Long, HorarioMedico> {
}
