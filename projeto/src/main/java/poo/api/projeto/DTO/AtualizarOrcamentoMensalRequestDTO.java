package poo.api.projeto.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AtualizarOrcamentoMensalRequestDTO {

    @NotNull(message = "o mes de referencia é obrigatorio!")
    private LocalDate mesRef;

    @NotNull(message = "o limite é obrigatorio!")
    @DecimalMin(value = "0.01", message = "o limite deve ser maior que zero!")
    private BigDecimal limite;

    @NotNull(message = "o gasto ate agora é obrigatorio!")
    @DecimalMin(value = "0.00", message = "o gasto nao pode ser negativo!")
    private BigDecimal gastoAteAgora;

    @NotBlank(message = "o status é obrigatorio!")
    private String status;
}
