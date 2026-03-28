package com.app.DocCenter.Repository.Paciente;
import com.app.DocCenter.Model.Paciente.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AlergiaRepository extends JpaRepository<Long, Alergia> {
}
