package poo.api.projeto.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poo.api.projeto.DTO.AtualizarTransacaoRequestDTO;
import poo.api.projeto.DTO.TransacaoRequestDTO;
import poo.api.projeto.DTO.TransacaoResponseDTO;
import poo.api.projeto.model.TransacaoModel;
import poo.api.projeto.service.TransacaoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<List<TransacaoResponseDTO>> listarTodas() {
        List<TransacaoResponseDTO> lista = transacaoService.listarTodas()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TransacaoResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<TransacaoResponseDTO> lista = transacaoService.listarPorUsuario(usuarioId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/centro/{centroDeCustoId}")
    public ResponseEntity<List<TransacaoResponseDTO>> listarPorCentroDeCusto(@PathVariable Long centroDeCustoId) {
        List<TransacaoResponseDTO> lista = transacaoService.listarPorCentroDeCusto(centroDeCustoId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        TransacaoModel transacao = transacaoService.buscarPorId(id);
        return ResponseEntity.ok(convertToDTO(transacao));
    }

    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> criar(@Valid @RequestBody TransacaoRequestDTO dto) {
        TransacaoModel transacao = transacaoService.criarTransacao(dto);
        return ResponseEntity.ok(convertToDTO(transacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarTransacaoRequestDTO dto) {

        TransacaoModel transacao = transacaoService.atualizarTransacao(id, dto);
        return ResponseEntity.ok(convertToDTO(transacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        transacaoService.deletarTransacao(id);
        return ResponseEntity.noContent().build();
    }

    private TransacaoResponseDTO convertToDTO(TransacaoModel t) {
        return new TransacaoResponseDTO(
                t.getId(),
                t.getUsuario().getId(),
                t.getCentroDeCusto().getId(),
                t.getTipo(),
                t.getValor(),
                t.getDescricao(),
                t.getData(),
                t.getCriadoEm()
        );
    }
}
