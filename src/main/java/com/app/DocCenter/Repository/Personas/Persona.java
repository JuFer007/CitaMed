package com.app.DocCenter.Repository.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface Persona extends JpaRepository<Long, Persona> {
}
