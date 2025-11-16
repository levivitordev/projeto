package poo.api.projeto.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AtualizarCentroDeCustoRequestDTO {

    @NotBlank(message = "o nome do centro de custo é obrigatorio!")
    private String nome;

    @NotBlank(message = "a cor (hex) é obrigatoria!")
    private String corHex;

    @NotBlank(message = "o icone é obrigatorio!")
    private String icone;
}
