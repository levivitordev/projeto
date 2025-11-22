package poo.api.projeto.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ContribuirMetaRequestDTO {

    @NotNull(message = "o valor da contribuicao é obrigatorio!")
    @DecimalMin(value = "0.01", message = "o valor da contribuicao deve ser maior que zero!")
    private BigDecimal valor;
}
