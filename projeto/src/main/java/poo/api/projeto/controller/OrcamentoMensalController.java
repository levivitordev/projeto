package poo.api.projeto.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poo.api.projeto.DTO.AtualizarOrcamentoMensalRequestDTO;
import poo.api.projeto.DTO.OrcamentoMensalRequestDTO;
import poo.api.projeto.DTO.OrcamentoMensalResponseDTO;
import poo.api.projeto.model.OrcamentoMensalModel;
import poo.api.projeto.service.OrcamentoMensalService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orcamentos-mensais")
public class OrcamentoMensalController {

    @Autowired
    private OrcamentoMensalService orcamentoMensalService;

    @GetMapping
    public ResponseEntity<List<OrcamentoMensalResponseDTO>> listarTodos() {
        List<OrcamentoMensalResponseDTO> lista = orcamentoMensalService.listarTodos()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<OrcamentoMensalResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<OrcamentoMensalResponseDTO> lista = orcamentoMensalService.listarPorUsuario(usuarioId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/centro/{centroDeCustoId}")
    public ResponseEntity<List<OrcamentoMensalResponseDTO>> listarPorCentroDeCusto(@PathVariable Long centroDeCustoId) {
        List<OrcamentoMensalResponseDTO> lista = orcamentoMensalService.listarPorCentroDeCusto(centroDeCustoId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrcamentoMensalResponseDTO> buscarPorId(@PathVariable Long id) {
        OrcamentoMensalModel orc = orcamentoMensalService.buscarPorId(id);
        return ResponseEntity.ok(convertToDTO(orc));
    }

    @PostMapping
    public ResponseEntity<OrcamentoMensalResponseDTO> criar(@Valid @RequestBody OrcamentoMensalRequestDTO dto) {
        OrcamentoMensalModel orc = orcamentoMensalService.criarOrcamento(dto);
        return ResponseEntity.ok(convertToDTO(orc));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrcamentoMensalResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarOrcamentoMensalRequestDTO dto) {

        OrcamentoMensalModel orc = orcamentoMensalService.atualizarOrcamento(id, dto);
        return ResponseEntity.ok(convertToDTO(orc));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        orcamentoMensalService.deletarOrcamento(id);
        return ResponseEntity.noContent().build();
    }

    private OrcamentoMensalResponseDTO convertToDTO(OrcamentoMensalModel o) {
        return new OrcamentoMensalResponseDTO(
                o.getId(),
                o.getUsuario().getId(),
                o.getCentroDeCusto().getId(),
                o.getMesRef(),
                o.getLimite(),
                o.getGastoAteAgora(),
                o.getStatus(),
                o.calcularUsoPercentual(),
                o.getCriadoEm()
        );
    }
}
