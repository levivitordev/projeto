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
public class MetaRequestDTO {

    @NotNull(message = "o id do usuario é obrigatorio!")
    private Long usuarioId;

    @NotNull(message = "o id do centro de custo é obrigatorio!")
    private Long centroDeCustoId;

    @NotBlank(message = "o titulo da meta é obrigatorio!")
    private String titulo;

    @NotNull(message = "o valor alvo é obrigatorio!")
    @DecimalMin(value = "0.01", message = "o valor alvo deve ser maior que zero!")
    private BigDecimal alvo;

    @NotNull(message = "a data alvo é obrigatoria!")
    private LocalDate dataAlvo;

    // opcional: se não vier, o service coloca "ATIVA"
    private String status;
}
