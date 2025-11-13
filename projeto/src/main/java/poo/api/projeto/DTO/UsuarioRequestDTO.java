package poo.api.projeto.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor

public class UsuarioRequestDTO {
    @NotBlank(message = "seu nome é obrigatorio!")

    private String nome;

    @NotBlank(message = "seu email é obrigatorio!")
    @Email(message = "voce deve colocar um email valdio!")
    private String email;

    @NotNull(message = "idade é obrigatorio!")
    @Positive(message = "insira uma idade valida!")
    private Integer idade;

    @NotNull(message = "ter uma senha é obrigatorio!")
    @Size(min = 6, message="sua senha deve conter no minimo 6 caracteres")
    private String senha;
}
