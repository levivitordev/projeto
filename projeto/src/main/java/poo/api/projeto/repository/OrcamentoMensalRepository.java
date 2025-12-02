package poo.api.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poo.api.projeto.model.OrcamentoMensalModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrcamentoMensalRepository extends JpaRepository<OrcamentoMensalModel, Long> {

    List<OrcamentoMensalModel> findByUsuarioId(Long usuarioId);

    List<OrcamentoMensalModel> findByCentroDeCustoId(Long centroDeCustoId);

    Optional<OrcamentoMensalModel> findByUsuarioIdAndCentroDeCustoIdAndMesRef(
            Long usuarioId,
            Long centroDeCustoId,
            LocalDate mesRef
    );
}
