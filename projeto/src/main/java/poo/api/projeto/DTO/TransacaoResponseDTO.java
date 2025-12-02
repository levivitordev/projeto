package poo.api.projeto.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TransacaoResponseDTO {

    private Long id;
    private Long usuarioId;
    private Long centroDeCustoId;
    private String tipo;
    private BigDecimal valor;
    private String descricao;
    private LocalDate data;
    private LocalDateTime criadoEm;
}
