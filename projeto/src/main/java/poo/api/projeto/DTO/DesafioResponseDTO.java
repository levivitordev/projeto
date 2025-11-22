package poo.api.projeto.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DesafioResponseDTO {

    private Long id;
    private Long usuarioId;
    private String titulo;
    private String descricao;
    private Integer pontos;
    private String status;
    private LocalDateTime criadoEm;
}
