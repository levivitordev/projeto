package poo.api.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poo.api.projeto.DTO.AtualizarTransacaoRequestDTO;
import poo.api.projeto.DTO.TransacaoRequestDTO;
import poo.api.projeto.model.CentroDeCustoModel;
import poo.api.projeto.model.TransacaoModel;
import poo.api.projeto.model.UsuarioModel;
import poo.api.projeto.repository.CentroDeCustoRepository;
import poo.api.projeto.repository.TransacaoRepository;
import poo.api.projeto.repository.UsuarioRepository;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CentroDeCustoRepository centroDeCustoRepository;

    @Transactional(readOnly = true)
    public List<TransacaoModel> listarTodas() {
        return transacaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<TransacaoModel> listarPorUsuario(Long usuarioId) {
        return transacaoRepository.findByUsuarioId(usuarioId);
    }

    @Transactional(readOnly = true)
    public List<TransacaoModel> listarPorCentroDeCusto(Long centroDeCustoId) {
        return transacaoRepository.findByCentroDeCustoId(centroDeCustoId);
    }

    @Transactional(readOnly = true)
    public TransacaoModel buscarPorId(Long id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("transacao com ID: " + id + " nao encontrada!"));
    }

    @Transactional
    public TransacaoModel criarTransacao(TransacaoRequestDTO dto) {

        UsuarioModel usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("usuario com ID: " + dto.getUsuarioId() + " nao encontrado!"));

        CentroDeCustoModel centro = centroDeCustoRepository.findById(dto.getCentroDeCustoId())
                .orElseThrow(() -> new RuntimeException("centro de custo com ID: " + dto.getCentroDeCustoId() + " nao encontrado!"));

        validarTipo(dto.getTipo());

        TransacaoModel transacao = new TransacaoModel();
        transacao.setUsuario(usuario);
        transacao.setCentroDeCusto(centro);
        transacao.setTipo(dto.getTipo());
        transacao.setValor(dto.getValor());
        transacao.setDescricao(dto.getDescricao());
        transacao.setData(dto.getData());

        return transacaoRepository.save(transacao);
    }

    @Transactional
    public TransacaoModel atualizarTransacao(Long id, AtualizarTransacaoRequestDTO dto) {

        TransacaoModel transacao = buscarPorId(id);

        CentroDeCustoModel centro = centroDeCustoRepository.findById(dto.getCentroDeCustoId())
                .orElseThrow(() -> new RuntimeException("centro de custo com ID: " + dto.getCentroDeCustoId() + " nao encontrado!"));

        validarTipo(dto.getTipo());

        transacao.setCentroDeCusto(centro);
        transacao.setTipo(dto.getTipo());
        transacao.setValor(dto.getValor());
        transacao.setDescricao(dto.getDescricao());
        transacao.setData(dto.getData());

        return transacaoRepository.save(transacao);
    }

    @Transactional
    public void deletarTransacao(Long id) {
        TransacaoModel transacao = buscarPorId(id);
        transacaoRepository.delete(transacao);
    }

    private void validarTipo(String tipo) {
        if (tipo == null) {
            throw new RuntimeException("tipo da transacao é obrigatorio!");
        }

        String t = tipo.toLowerCase().trim();
        if (!t.equals("entrada") && !t.equals("saida")) {
            throw new RuntimeException("tipo invalido! use 'entrada' ou 'saida'.");
        }
    }
}
