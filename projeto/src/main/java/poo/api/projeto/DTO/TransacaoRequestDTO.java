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
public class TransacaoRequestDTO {

    @NotNull(message = "o id do usuario é obrigatorio!")
    private Long usuarioId;

    @NotNull(message = "o id do centro de custo é obrigatorio!")
    private Long centroDeCustoId;

    @NotBlank(message = "o tipo da transacao é obrigatorio! (entrada/saida)")
    private String tipo;

    @NotNull(message = "o valor é obrigatorio!")
    @DecimalMin(value = "0.01", message = "o valor deve ser maior que zero!")
    private BigDecimal valor;

    @NotBlank(message = "a descricao é obrigatoria!")
    private String descricao;

    @NotNull(message = "a data é obrigatoria!")
    private LocalDate data;
}
