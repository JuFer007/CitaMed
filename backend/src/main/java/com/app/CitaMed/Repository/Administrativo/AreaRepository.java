package com.app.CitaMed.Repository.Administrativo;
import com.app.CitaMed.Model.Administrativo.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AreaRepository extends JpaRepository<Area, Long> {
    boolean existsByNombre(String nombre);
}
