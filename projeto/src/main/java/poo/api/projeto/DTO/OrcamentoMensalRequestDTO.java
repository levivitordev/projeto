package poo.api.projeto.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OrcamentoMensalRequestDTO {

    @NotNull(message = "o id do usuario é obrigatorio!")
    private Long usuarioId;

    @NotNull(message = "o id do centro de custo é obrigatorio!")
    private Long centroDeCustoId;

    @NotNull(message = "o mes de referencia é obrigatorio!")
    private LocalDate mesRef;

    @NotNull(message = "o limite é obrigatorio!")
    @DecimalMin(value = "0.01", message = "o limite deve ser maior que zero!")
    private BigDecimal limite;

    // opcional: se não vier, o service seta "ATIVO"
    private String status;
}
