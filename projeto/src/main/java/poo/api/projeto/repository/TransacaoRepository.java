package poo.api.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poo.api.projeto.model.TransacaoModel;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoModel, Long> {

    // pela propriedade aninhada usuario.id
    List<TransacaoModel> findByUsuarioId(Long usuarioId);

    // pela propriedade aninhada centroDeCusto.id
    List<TransacaoModel> findByCentroDeCustoId(Long centroDeCustoId);

    // opcional: filtrar por usuario + tipo
    List<TransacaoModel> findByUsuarioIdAndTipoIgnoreCase(Long usuarioId, String tipo);
}
