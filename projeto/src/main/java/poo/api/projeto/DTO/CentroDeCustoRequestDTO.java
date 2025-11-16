package poo.api.projeto.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CentroDeCustoRequestDTO {

    @NotNull(message = "o id do usuario é obrigatorio!")
    private Long usuarioId;

    @NotBlank(message = "o nome do centro de custo é obrigatorio!")
    private String nome;

    @NotBlank(message = "a cor (hex) é obrigatoria!")
    private String corHex;

    @NotBlank(message = "o icone é obrigatorio!")
    private String icone;
}
