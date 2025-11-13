package poo.api.projeto.service;

import poo.api.projeto.DTO.LoginRequestDTO;
import poo.api.projeto.model.UsuarioModel;
import poo.api.projeto.DTO.AtualizarPerfilRequestDTO;
import poo.api.projeto.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<UsuarioModel> getAllUsuario() {
        return usuarioRepository.findAll();
    }
    @Transactional(readOnly = true)
    public UsuarioModel getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("usuario com ID: " + id +" nao encontrado!"));
    }
    public UsuarioModel createUsuario(UsuarioModel usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Esse email ja existe!");
        }
        return usuarioRepository.save(usuario);
    }
    @Transactional
    public UsuarioModel atualizarPerfil(Long id, AtualizarPerfilRequestDTO request){
        UsuarioModel usuario= getUsuarioById(id);
        usuario.atualizarPerfil(request.getNome(), request.getIdade());
        return usuarioRepository.save(usuario);
    }
    @Transactional
    public UsuarioModel acumularPontos(Long usuarioId, Integer pontos) {
        UsuarioModel usuario= getUsuarioById(usuarioId);
        usuario.acumularPontos(pontos);
        return usuarioRepository.save(usuario);
    }
    @Transactional
    public void deletarUsuario(Long id) {
        UsuarioModel usuario= getUsuarioById(id);
        usuarioRepository.delete(usuario);
    }
    @Transactional(readOnly = true)
    public UsuarioModel getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("usuario com email: " + email + " nao encontrado!"));
    }
    @Transactional(readOnly = true)
    public UsuarioModel autenticarUsuario(LoginRequestDTO  loginRequestDTO) {
        UsuarioModel usuario= usuarioRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("email ou senha invalidos!"));

        if (usuario.getSenha().equals(loginRequestDTO.getSenha())) {
            return usuario;
        }else  {
            throw new RuntimeException("email ou senha invalidos!");
        }
    }

}

