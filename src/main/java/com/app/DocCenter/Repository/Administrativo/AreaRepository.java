package com.app.DocCenter.Repository.Administrativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AreaRepository extends JpaRepository<Long, AreaRepository> {
}
