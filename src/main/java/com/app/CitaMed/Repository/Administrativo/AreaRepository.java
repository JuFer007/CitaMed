package com.app.DocCenter.Repository.Administrativo;
import com.app.DocCenter.Model.Administrativo.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AreaRepository extends JpaRepository<Area, Long> {
    boolean existsByNombre(String nombre);
}
