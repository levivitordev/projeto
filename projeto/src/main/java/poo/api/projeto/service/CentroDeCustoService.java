package poo.api.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poo.api.projeto.DTO.AtualizarCentroDeCustoRequestDTO;
import poo.api.projeto.DTO.CentroDeCustoRequestDTO;
import poo.api.projeto.model.CentroDeCustoModel;
import poo.api.projeto.model.UsuarioModel;
import poo.api.projeto.repository.CentroDeCustoRepository;
import poo.api.projeto.repository.UsuarioRepository;

import java.util.List;

@Service
public class CentroDeCustoService {

    @Autowired
    private CentroDeCustoRepository centroDeCustoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<CentroDeCustoModel> listarTodos() {
        return centroDeCustoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<CentroDeCustoModel> listarPorUsuario(Long usuarioId) {
        return centroDeCustoRepository.findByUsuarioId(usuarioId);
    }

    @Transactional(readOnly = true)
    public CentroDeCustoModel buscarPorId(Long id) {
        return centroDeCustoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("centro de custo com ID: " + id + " nao encontrado!"));
    }

    @Transactional
    public CentroDeCustoModel criarCentroDeCusto(CentroDeCustoRequestDTO dto) {

        UsuarioModel usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("usuario com ID: " + dto.getUsuarioId() + " nao encontrado!"));

        if (centroDeCustoRepository.existsByUsuarioIdAndNomeIgnoreCase(dto.getUsuarioId(), dto.getNome())) {
            throw new RuntimeException("esse centro de custo já existe para este usuario!");
        }

        CentroDeCustoModel centro = new CentroDeCustoModel();
        centro.setUsuario(usuario);
        centro.setNome(dto.getNome());
        centro.setCorHex(dto.getCorHex());
        centro.setIcone(dto.getIcone());

        return centroDeCustoRepository.save(centro);
    }

    @Transactional
    public CentroDeCustoModel atualizarCentroDeCusto(Long id, AtualizarCentroDeCustoRequestDTO dto) {
        CentroDeCustoModel centro = buscarPorId(id);
        centro.atualizarDados(dto.getNome(), dto.getCorHex(), dto.getIcone());
        return centroDeCustoRepository.save(centro);
    }

    @Transactional
    public void deletarCentroDeCusto(Long id) {
        CentroDeCustoModel centro = buscarPorId(id);
        centroDeCustoRepository.delete(centro);
    }
}
