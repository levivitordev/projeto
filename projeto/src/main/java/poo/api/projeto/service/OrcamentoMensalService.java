package poo.api.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poo.api.projeto.DTO.AtualizarOrcamentoMensalRequestDTO;
import poo.api.projeto.DTO.OrcamentoMensalRequestDTO;
import poo.api.projeto.model.CentroDeCustoModel;
import poo.api.projeto.model.OrcamentoMensalModel;
import poo.api.projeto.model.UsuarioModel;
import poo.api.projeto.repository.CentroDeCustoRepository;
import poo.api.projeto.repository.OrcamentoMensalRepository;
import poo.api.projeto.repository.UsuarioRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrcamentoMensalService {

    @Autowired
    private OrcamentoMensalRepository orcamentoMensalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CentroDeCustoRepository centroDeCustoRepository;

    @Transactional(readOnly = true)
    public List<OrcamentoMensalModel> listarTodos() {
        return orcamentoMensalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<OrcamentoMensalModel> listarPorUsuario(Long usuarioId) {
        return orcamentoMensalRepository.findByUsuarioId(usuarioId);
    }

    @Transactional(readOnly = true)
    public List<OrcamentoMensalModel> listarPorCentroDeCusto(Long centroDeCustoId) {
        return orcamentoMensalRepository.findByCentroDeCustoId(centroDeCustoId);
    }

    @Transactional(readOnly = true)
    public OrcamentoMensalModel buscarPorId(Long id) {
        return orcamentoMensalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("orcamento mensal com ID: " + id + " nao encontrado!"));
    }

    @Transactional
    public OrcamentoMensalModel criarOrcamento(OrcamentoMensalRequestDTO dto) {

        UsuarioModel usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("usuario com ID: " + dto.getUsuarioId() + " nao encontrado!"));

        CentroDeCustoModel centro = centroDeCustoRepository.findById(dto.getCentroDeCustoId())
                .orElseThrow(() -> new RuntimeException("centro de custo com ID: " + dto.getCentroDeCustoId() + " nao encontrado!"));

        // Evitar orçamento duplicado para mesmo usuario + centro + mes
        orcamentoMensalRepository.findByUsuarioIdAndCentroDeCustoIdAndMesRef(
                dto.getUsuarioId(),
                dto.getCentroDeCustoId(),
                dto.getMesRef()
        ).ifPresent(existing -> {
            throw new RuntimeException("ja existe um orcamento para esse usuario, centro de custo e mes de referencia!");
        });

        OrcamentoMensalModel orc = new OrcamentoMensalModel();
        orc.setUsuario(usuario);
        orc.setCentroDeCusto(centro);
        orc.setMesRef(dto.getMesRef());
        orc.setLimite(dto.getLimite());
        orc.setGastoAteAgora(BigDecimal.ZERO);
        orc.setStatus(dto.getStatus() != null && !dto.getStatus().trim().isEmpty()
                ? dto.getStatus()
                : "ATIVO");

        return orcamentoMensalRepository.save(orc);
    }

    @Transactional
    public OrcamentoMensalModel atualizarOrcamento(Long id, AtualizarOrcamentoMensalRequestDTO dto) {
        OrcamentoMensalModel orc = buscarPorId(id);

        orc.setMesRef(dto.getMesRef());
        orc.setLimite(dto.getLimite());
        orc.setGastoAteAgora(dto.getGastoAteAgora());
        orc.setStatus(dto.getStatus());

        return orcamentoMensalRepository.save(orc);
    }

    @Transactional
    public void deletarOrcamento(Long id) {
        OrcamentoMensalModel orc = buscarPorId(id);
        orcamentoMensalRepository.delete(orc);
    }
}
