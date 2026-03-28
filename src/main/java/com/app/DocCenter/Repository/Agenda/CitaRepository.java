package com.app.DocCenter.Repository.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CitaRepository extends JpaRepository<Long, CitaRepository> {
}
