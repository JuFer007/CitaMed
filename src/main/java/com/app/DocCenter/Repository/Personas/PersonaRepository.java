package com.app.DocCenter.Repository.Personas;
import com.app.DocCenter.Model.Personas.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    boolean existsByDni(String dni);
    Persona findByDni(String dni);
}
