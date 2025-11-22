package poo.api.projeto.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AtualizarProgressoDesafioRequestDTO {

    @NotNull(message = "o valor de progresso é obrigatorio!")
    @Min(value = 1, message = "o valor de progresso deve ser pelo menos 1!")
    private Integer valor;
}
