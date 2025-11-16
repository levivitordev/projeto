package poo.api.projeto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "centros_de_custo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CentroDeCustoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento N:1 com UsuarioModel
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;

    @Column(nullable = false, length = 100)
    private String nome;


    @Column(nullable = false, length = 7)
    private String corHex;


    @Column(nullable = false, length = 50)
    private String icone;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime criadoEm;


    public void atualizarDados(String nome, String corHex, String icone) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
        if (corHex != null && !corHex.trim().isEmpty()) {
            this.corHex = corHex;
        }
        if (icone != null && !icone.trim().isEmpty()) {
            this.icone = icone;
        }
    }
}
