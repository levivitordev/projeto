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
public class MetaResponseDTO {

    private Long id;
    private Long usuarioId;
    private Long centroDeCustoId;
    private String titulo;
    private BigDecimal alvo;
    private BigDecimal atual;
    private String status;
    private LocalDate dataAlvo;
    private BigDecimal progresso;
    private LocalDateTime criadoEm;
}
