package poo.api.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poo.api.projeto.DTO.AtualizarMetaRequestDTO;
import poo.api.projeto.DTO.ContribuirMetaRequestDTO;
import poo.api.projeto.DTO.MetaRequestDTO;
import poo.api.projeto.model.CentroDeCustoModel;
import poo.api.projeto.model.MetaModel;
import poo.api.projeto.model.UsuarioModel;
import poo.api.projeto.repository.CentroDeCustoRepository;
import poo.api.projeto.repository.MetaRepository;
import poo.api.projeto.repository.UsuarioRepository;

import java.util.List;

@Service
public class MetaService {

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CentroDeCustoRepository centroDeCustoRepository;

    @Transactional(readOnly = true)
    public List<MetaModel> listarTodas() {
        return metaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<MetaModel> listarPorUsuario(Long usuarioId) {
        return metaRepository.findByUsuarioId(usuarioId);
    }

    @Transactional(readOnly = true)
    public List<MetaModel> listarPorCentroDeCusto(Long centroDeCustoId) {
        return metaRepository.findByCentroDeCustoId(centroDeCustoId);
    }

    @Transactional(readOnly = true)
    public MetaModel buscarPorId(Long id) {
        return metaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("meta com ID: " + id + " nao encontrada!"));
    }

    @Transactional
    public MetaModel criarMeta(MetaRequestDTO dto) {

        UsuarioModel usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("usuario com ID: " + dto.getUsuarioId() + " nao encontrado!"));

        CentroDeCustoModel centro = centroDeCustoRepository.findById(dto.getCentroDeCustoId())
                .orElseThrow(() -> new RuntimeException("centro de custo com ID: " + dto.getCentroDeCustoId() + " nao encontrado!"));

        MetaModel meta = new MetaModel();
        meta.setUsuario(usuario);
        meta.setCentroDeCusto(centro);
        meta.setTitulo(dto.getTitulo());
        meta.setAlvo(dto.getAlvo());
        meta.setAtual(dto.getAlvo().min(dto.getAlvo().ZERO)); // atual começa em 0
        meta.setAtual(meta.getAtual()); // só pra garantir zero se der confusão
        meta.setStatus(dto.getStatus() != null && !dto.getStatus().trim().isEmpty()
                ? dto.getStatus()
                : "ATIVA");
        meta.setDataAlvo(dto.getDataAlvo());

        // melhor: atual explicitamente zero
        meta.setAtual(java.math.BigDecimal.ZERO);

        return metaRepository.save(meta);
    }

    @Transactional
    public MetaModel atualizarMeta(Long id, AtualizarMetaRequestDTO dto) {
        MetaModel meta = buscarPorId(id);

        meta.setTitulo(dto.getTitulo());
        meta.setAlvo(dto.getAlvo());
        meta.setAtual(dto.getAtual());
        meta.setStatus(dto.getStatus());
        meta.setDataAlvo(dto.getDataAlvo());

        return metaRepository.save(meta);
    }

    @Transactional
    public MetaModel contribuir(Long id, ContribuirMetaRequestDTO dto) {
        MetaModel meta = buscarPorId(id);
        meta.contribuir(dto.getValor());
        return metaRepository.save(meta);
    }

    @Transactional
    public MetaModel cancelar(Long id) {
        MetaModel meta = buscarPorId(id);
        meta.cancelar();
        return metaRepository.save(meta);
    }

    @Transactional
    public MetaModel concluir(Long id) {
        MetaModel meta = buscarPorId(id);
        meta.concluir();
        return metaRepository.save(meta);
    }

    @Transactional
    public void deletarMeta(Long id) {
        MetaModel meta = buscarPorId(id);
        metaRepository.delete(meta);
    }
}
