package poo.api.projeto.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poo.api.projeto.DTO.AtualizarMetaRequestDTO;
import poo.api.projeto.DTO.ContribuirMetaRequestDTO;
import poo.api.projeto.DTO.MetaRequestDTO;
import poo.api.projeto.DTO.MetaResponseDTO;
import poo.api.projeto.model.MetaModel;
import poo.api.projeto.service.MetaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/metas")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @GetMapping
    public ResponseEntity<List<MetaResponseDTO>> listarTodas() {
        List<MetaResponseDTO> lista = metaService.listarTodas()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MetaResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<MetaResponseDTO> lista = metaService.listarPorUsuario(usuarioId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/centro/{centroDeCustoId}")
    public ResponseEntity<List<MetaResponseDTO>> listarPorCentroDeCusto(@PathVariable Long centroDeCustoId) {
        List<MetaResponseDTO> lista = metaService.listarPorCentroDeCusto(centroDeCustoId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetaResponseDTO> buscarPorId(@PathVariable Long id) {
        MetaModel meta = metaService.buscarPorId(id);
        return ResponseEntity.ok(convertToDTO(meta));
    }

    @PostMapping
    public ResponseEntity<MetaResponseDTO> criar(@Valid @RequestBody MetaRequestDTO dto) {
        MetaModel meta = metaService.criarMeta(dto);
        return ResponseEntity.ok(convertToDTO(meta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarMetaRequestDTO dto) {

        MetaModel meta = metaService.atualizarMeta(id, dto);
        return ResponseEntity.ok(convertToDTO(meta));
    }

    @PostMapping("/{id}/contribuir")
    public ResponseEntity<MetaResponseDTO> contribuir(
            @PathVariable Long id,
            @Valid @RequestBody ContribuirMetaRequestDTO dto) {

        MetaModel meta = metaService.contribuir(id, dto);
        return ResponseEntity.ok(convertToDTO(meta));
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<MetaResponseDTO> cancelar(@PathVariable Long id) {
        MetaModel meta = metaService.cancelar(id);
        return ResponseEntity.ok(convertToDTO(meta));
    }

    @PostMapping("/{id}/concluir")
    public ResponseEntity<MetaResponseDTO> concluir(@PathVariable Long id) {
        MetaModel meta = metaService.concluir(id);
        return ResponseEntity.ok(convertToDTO(meta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        metaService.deletarMeta(id);
        return ResponseEntity.noContent().build();
    }

    private MetaResponseDTO convertToDTO(MetaModel m) {
        return new MetaResponseDTO(
                m.getId(),
                m.getUsuario().getId(),
                m.getCentroDeCusto().getId(),
                m.getTitulo(),
                m.getAlvo(),
                m.getAtual(),
                m.getStatus(),
                m.getDataAlvo(),
                m.progresso(),
                m.getCriadoEm()
        );
    }
}
