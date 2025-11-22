package poo.api.projeto.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DesafioRequestDTO {

    @NotNull(message = "o id do usuario é obrigatorio!")
    private Long usuarioId;

    @NotBlank(message = "o titulo do desafio é obrigatorio!")
    private String titulo;

    @NotBlank(message = "a descricao do desafio é obrigatoria!")
    private String descricao;

    @NotNull(message = "os pontos do desafio sao obrigatorios!")
    @Min(value = 1, message = "os pontos devem ser pelo menos 1!")
    private Integer pontos;

    // opcional: se não vier, o service coloca "ATIVO"
    private String status;
}
