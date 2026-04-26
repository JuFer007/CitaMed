package com.app.CitaMed.Repository.Administrativo;
import com.app.CitaMed.Model.Administrativo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByUserName(String userName);
    Usuario findByUserName(String userName);
}
