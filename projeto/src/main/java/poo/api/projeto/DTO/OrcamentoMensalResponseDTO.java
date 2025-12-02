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
public class OrcamentoMensalResponseDTO {

    private Long id;
    private Long usuarioId;
    private Long centroDeCustoId;
    private LocalDate mesRef;
    private BigDecimal limite;
    private BigDecimal gastoAteAgora;
    private String status;
    private BigDecimal usoPercentual;
    private LocalDateTime criadoEm;
}
