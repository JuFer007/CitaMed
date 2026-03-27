package com.app.DocCenter.Repository.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface Tratamiento extends JpaRepository<Long, Tratamiento> {
}
