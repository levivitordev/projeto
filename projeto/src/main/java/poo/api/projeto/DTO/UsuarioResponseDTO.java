package poo.api.projeto.DTO;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private Integer idade;
    private Integer pontos;
    private LocalDateTime criadoEm;

}
