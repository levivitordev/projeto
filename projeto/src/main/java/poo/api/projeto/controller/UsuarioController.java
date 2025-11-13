package poo.api.projeto.controller;

import poo.api.projeto.DTO.LoginRequestDTO;
import poo.api.projeto.model.UsuarioModel;
import poo.api.projeto.DTO.AtualizarPerfilRequestDTO;
import poo.api.projeto.DTO.UsuarioRequestDTO;
import poo.api.projeto.DTO.UsuarioResponseDTO;
import poo.api.projeto.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioService.getAllUsuario().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable Long id) {
        UsuarioModel usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(convertToDTO(usuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setNome(usuarioRequestDTO.getNome());
        usuarioModel.setEmail(usuarioRequestDTO.getEmail());
        usuarioModel.setIdade(usuarioRequestDTO.getIdade());
        usuarioModel.setSenha(usuarioRequestDTO.getSenha());

        UsuarioModel savedUsuario = usuarioService.createUsuario(usuarioModel);
        return ResponseEntity.ok(convertToDTO(savedUsuario));
    }

    @PutMapping("/{id}/perfil")
    public ResponseEntity<UsuarioResponseDTO> atualizarPerfil(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarPerfilRequestDTO atualizarPerfilRequestDTO) {

        UsuarioModel updateUsuario = usuarioService.atualizarPerfil(id, atualizarPerfilRequestDTO);
        return ResponseEntity.ok(convertToDTO(updateUsuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioByEmail(@PathVariable String email) {
        UsuarioModel usuarioModel = usuarioService.getUsuarioByEmail(email);
        return ResponseEntity.ok(convertToDTO(usuarioModel));
    }
    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        UsuarioModel usuarioAutenticado = usuarioService.autenticarUsuario(loginRequestDTO);
        UsuarioResponseDTO usuarioResponseDTO = convertToDTO(usuarioAutenticado);

        return ResponseEntity.ok(usuarioResponseDTO);
    }

    private UsuarioResponseDTO convertToDTO(UsuarioModel usuarioModel) {
        return new UsuarioResponseDTO(
                usuarioModel.getId(),
                usuarioModel.getNome(),
                usuarioModel.getEmail(),
                usuarioModel.getIdade(),
                usuarioModel.getPontos(),
                usuarioModel.getCriadoEm()
        );
    }
}



