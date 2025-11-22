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
public class AtualizarMetaRequestDTO {

    @NotBlank(message = "o titulo da meta é obrigatorio!")
    private String titulo;

    @NotNull(message = "o valor alvo é obrigatorio!")
    @DecimalMin(value = "0.01", message = "o valor alvo deve ser maior que zero!")
    private BigDecimal alvo;

    @NotNull(message = "o valor atual é obrigatorio!")
    @DecimalMin(value = "0.00", message = "o valor atual nao pode ser negativo!")
    private BigDecimal atual;

    @NotBlank(message = "o status é obrigatorio!")
    private String status;

    @NotNull(message = "a data alvo é obrigatoria!")
    private LocalDate dataAlvo;
}
