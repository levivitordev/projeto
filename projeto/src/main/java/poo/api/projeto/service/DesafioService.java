package poo.api.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poo.api.projeto.DTO.AtualizarDesafioRequestDTO;
import poo.api.projeto.DTO.AtualizarProgressoDesafioRequestDTO;
import poo.api.projeto.DTO.DesafioRequestDTO;
import poo.api.projeto.model.DesafioModel;
import poo.api.projeto.model.UsuarioModel;
import poo.api.projeto.repository.DesafioRepository;
import poo.api.projeto.repository.UsuarioRepository;

import java.util.List;

@Service
public class DesafioService {

    @Autowired
    private DesafioRepository desafioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<DesafioModel> listarTodos() {
        return desafioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<DesafioModel> listarPorUsuario(Long usuarioId) {
        return desafioRepository.findByUsuarioId(usuarioId);
    }

    @Transactional(readOnly = true)
    public DesafioModel buscarPorId(Long id) {
        return desafioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("desafio com ID: " + id + " nao encontrado!"));
    }

    @Transactional
    public DesafioModel criarDesafio(DesafioRequestDTO dto) {

        UsuarioModel usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("usuario com ID: " + dto.getUsuarioId() + " nao encontrado!"));

        DesafioModel desafio = new DesafioModel();
        desafio.setUsuario(usuario);
        desafio.setTitulo(dto.getTitulo());
        desafio.setDescricao(dto.getDescricao());
        desafio.setPontos(dto.getPontos());
        desafio.setStatus(dto.getStatus() != null && !dto.getStatus().trim().isEmpty()
                ? dto.getStatus()
                : "ATIVO");

        return desafioRepository.save(desafio);
    }

    @Transactional
    public DesafioModel atualizarDesafio(Long id, AtualizarDesafioRequestDTO dto) {
        DesafioModel desafio = buscarPorId(id);

        desafio.setTitulo(dto.getTitulo());
        desafio.setDescricao(dto.getDescricao());
        desafio.setPontos(dto.getPontos());
        desafio.setStatus(dto.getStatus());

        return desafioRepository.save(desafio);
    }

    @Transactional
    public DesafioModel participar(Long id) {
        DesafioModel desafio = buscarPorId(id);
        desafio.participar();
        return desafioRepository.save(desafio);
    }

    @Transactional
    public DesafioModel atualizarProgresso(Long id, AtualizarProgressoDesafioRequestDTO dto) {
        DesafioModel desafio = buscarPorId(id);
        desafio.atualizarProgresso(dto.getValor());
        return desafioRepository.save(desafio);
    }

    @Transactional
    public DesafioModel concluir(Long id) {
        DesafioModel desafio = buscarPorId(id);
        desafio.concluir();
        return desafioRepository.save(desafio);
    }

    @Transactional
    public void deletarDesafio(Long id) {
        DesafioModel desafio = buscarPorId(id);
        desafioRepository.delete(desafio);
    }
}
