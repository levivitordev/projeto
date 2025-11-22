package poo.api.projeto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "desafios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesafioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // N:1 com Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;

    @Column(nullable = false, length = 120)
    private String titulo;

    @Column(nullable = false, length = 255)
    private String descricao;

    // pontos que o desafio vale / recompensa
    @Column(nullable = false)
    private Integer pontos;

    // ATIVO, EM_ANDAMENTO, CONCLUIDO, CANCELADO etc.
    @Column(nullable = false, length = 20)
    private String status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime criadoEm;

    // ===== Métodos de “domínio” =====

    public void participar() {
        // quando o usuário começa o desafio
        this.status = "EM_ANDAMENTO";
    }

    public void atualizarProgresso(Integer valor) {
        // aqui você poderia somar pontos parciais ou registrar progresso.
        // por enquanto vamos interpretar como "incrementar pontos concluídos".
        if (valor != null && valor > 0) {
            this.pontos += valor;
        }
    }

    public void concluir() {
        this.status = "CONCLUIDO";
    }
}
