package poo.api.projeto.repository;

import poo.api.projeto.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository  extends JpaRepository<UsuarioModel, Long>{

    boolean existsByEmail(String email);
    Optional<UsuarioModel> findByEmail(String email);
}
