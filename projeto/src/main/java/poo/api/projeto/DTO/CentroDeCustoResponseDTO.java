package poo.api.projeto.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CentroDeCustoResponseDTO {

    private Long id;
    private Long usuarioId;
    private String nome;
    private String corHex;
    private String icone;
    private LocalDateTime criadoEm;
}
