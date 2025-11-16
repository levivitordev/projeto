package poo.api.projeto.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poo.api.projeto.DTO.AtualizarCentroDeCustoRequestDTO;
import poo.api.projeto.DTO.CentroDeCustoRequestDTO;
import poo.api.projeto.DTO.CentroDeCustoResponseDTO;
import poo.api.projeto.model.CentroDeCustoModel;
import poo.api.projeto.service.CentroDeCustoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/centros-de-custo")
public class CentroDeCustoController {

    @Autowired
    private CentroDeCustoService centroDeCustoService;

    @GetMapping
    public ResponseEntity<List<CentroDeCustoResponseDTO>> listarTodos() {
        List<CentroDeCustoResponseDTO> lista = centroDeCustoService.listarTodos()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CentroDeCustoResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<CentroDeCustoResponseDTO> lista = centroDeCustoService.listarPorUsuario(usuarioId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroDeCustoResponseDTO> buscarPorId(@PathVariable Long id) {
        CentroDeCustoModel centro = centroDeCustoService.buscarPorId(id);
        return ResponseEntity.ok(convertToDTO(centro));
    }

    @PostMapping
    public ResponseEntity<CentroDeCustoResponseDTO> criar(@Valid @RequestBody CentroDeCustoRequestDTO dto) {
        CentroDeCustoModel centro = centroDeCustoService.criarCentroDeCusto(dto);
        return ResponseEntity.ok(convertToDTO(centro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroDeCustoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarCentroDeCustoRequestDTO dto) {

        CentroDeCustoModel centro = centroDeCustoService.atualizarCentroDeCusto(id, dto);
        return ResponseEntity.ok(convertToDTO(centro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        centroDeCustoService.deletarCentroDeCusto(id);
        return ResponseEntity.noContent().build();
    }

    private CentroDeCustoResponseDTO convertToDTO(CentroDeCustoModel centro) {
        return new CentroDeCustoResponseDTO(
                centro.getId(),
                centro.getUsuario().getId(),
                centro.getNome(),
                centro.getCorHex(),
                centro.getIcone(),
                centro.getCriadoEm()
        );
    }
}
