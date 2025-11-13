package poo.api.projeto.DTO;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor

public class AtualizarPerfilRequestDTO {
    private String nome;

    @Positive(message = "insira uma idade valida")
    private Integer idade;

}
