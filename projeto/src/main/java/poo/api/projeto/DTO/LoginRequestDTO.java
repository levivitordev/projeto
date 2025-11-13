package poo.api.projeto.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDTO {
    private String email;
    private String senha;
}
