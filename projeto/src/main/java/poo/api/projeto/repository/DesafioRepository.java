package poo.api.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poo.api.projeto.model.DesafioModel;

import java.util.List;

@Repository
public interface DesafioRepository extends JpaRepository<DesafioModel, Long> {

    List<DesafioModel> findByUsuarioId(Long usuarioId);
}
