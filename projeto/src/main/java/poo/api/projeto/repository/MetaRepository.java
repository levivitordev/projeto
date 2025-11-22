package poo.api.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poo.api.projeto.model.MetaModel;

import java.util.List;

@Repository
public interface MetaRepository extends JpaRepository<MetaModel, Long> {

    List<MetaModel> findByUsuarioId(Long usuarioId);

    List<MetaModel> findByCentroDeCustoId(Long centroDeCustoId);
}
