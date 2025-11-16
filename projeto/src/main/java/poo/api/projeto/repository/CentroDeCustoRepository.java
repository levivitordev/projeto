package poo.api.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poo.api.projeto.model.CentroDeCustoModel;

import java.util.List;

@Repository
public interface CentroDeCustoRepository extends JpaRepository<CentroDeCustoModel, Long> {

    // usa a propriedade aninhada: centro.usuario.id
    List<CentroDeCustoModel> findByUsuarioId(Long usuarioId);

    boolean existsByUsuarioIdAndNomeIgnoreCase(Long usuarioId, String nome);
}
