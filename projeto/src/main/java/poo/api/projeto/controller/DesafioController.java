package poo.api.projeto.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poo.api.projeto.DTO.AtualizarDesafioRequestDTO;
import poo.api.projeto.DTO.AtualizarProgressoDesafioRequestDTO;
import poo.api.projeto.DTO.DesafioRequestDTO;
import poo.api.projeto.DTO.DesafioResponseDTO;
import poo.api.projeto.model.DesafioModel;
import poo.api.projeto.service.DesafioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/desafios")
public class DesafioController {

    @Autowired
    private DesafioService desafioService;

    @GetMapping
    public ResponseEntity<List<DesafioResponseDTO>> listarTodos() {
        List<DesafioResponseDTO> lista = desafioService.listarTodos()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DesafioResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<DesafioResponseDTO> lista = desafioService.listarPorUsuario(usuarioId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesafioResponseDTO> buscarPorId(@PathVariable Long id) {
        DesafioModel desafio = desafioService.buscarPorId(id);
        return ResponseEntity.ok(convertToDTO(desafio));
    }

    @PostMapping
    public ResponseEntity<DesafioResponseDTO> criar(@Valid @RequestBody DesafioRequestDTO dto) {
        DesafioModel desafio = desafioService.criarDesafio(dto);
        return ResponseEntity.ok(convertToDTO(desafio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DesafioResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarDesafioRequestDTO dto) {

        DesafioModel desafio = desafioService.atualizarDesafio(id, dto);
        return ResponseEntity.ok(convertToDTO(desafio));
    }

    @PostMapping("/{id}/participar")
    public ResponseEntity<DesafioResponseDTO> participar(@PathVariable Long id) {
        DesafioModel desafio = desafioService.participar(id);
        return ResponseEntity.ok(convertToDTO(desafio));
    }

    @PostMapping("/{id}/progresso")
    public ResponseEntity<DesafioResponseDTO> atualizarProgresso(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarProgressoDesafioRequestDTO dto) {

        DesafioModel desafio = desafioService.atualizarProgresso(id, dto);
        return ResponseEntity.ok(convertToDTO(desafio));
    }

    @PostMapping("/{id}/concluir")
    public ResponseEntity<DesafioResponseDTO> concluir(@PathVariable Long id) {
        DesafioModel desafio = desafioService.concluir(id);
        return ResponseEntity.ok(convertToDTO(desafio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        desafioService.deletarDesafio(id);
        return ResponseEntity.noContent().build();
    }

    private DesafioResponseDTO convertToDTO(DesafioModel d) {
        return new DesafioResponseDTO(
                d.getId(),
                d.getUsuario().getId(),
                d.getTitulo(),
                d.getDescricao(),
                d.getPontos(),
                d.getStatus(),
                d.getCriadoEm()
        );
    }
}
