package poo.api.projeto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "metas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // N:1 com Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;

    // N:1 com CentroDeCusto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centro_de_custo_id", nullable = false)
    private CentroDeCustoModel centroDeCusto;

    @Column(nullable = false, length = 120)
    private String titulo;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal alvo;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal atual = BigDecimal.ZERO;

    @Column(nullable = false, length = 20)
    private String status; // ATIVA, CONCLUIDA, CANCELADA, etc.

    @Column(nullable = false)
    private LocalDate dataAlvo;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime criadoEm;

    // ===== Métodos de domínio =====

    // Retorna o progresso em %, com 2 casas decimais
    public BigDecimal progresso() {
        if (alvo == null || alvo.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return atual
                .multiply(BigDecimal.valueOf(100))
                .divide(alvo, 2, RoundingMode.HALF_UP);
    }

    public void contribuir(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("o valor da contribuicao deve ser maior que zero!");
        }
        this.atual = this.atual.add(valor);
    }

    public void cancelar() {
        this.status = "CANCELADA";
    }

    public void concluir() {
        this.status = "CONCLUIDA";
    }
}
