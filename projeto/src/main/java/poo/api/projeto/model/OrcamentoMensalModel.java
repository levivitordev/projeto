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
@Table(name = "orcamentos_mensais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrcamentoMensalModel {

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

    // mês/ano de referência do orçamento
    @Column(nullable = false)
    private LocalDate mesRef;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal limite;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal gastoAteAgora = BigDecimal.ZERO;

    @Column(nullable = false, length = 20)
    private String status; // ex.: "ATIVO", "ALERTA", "ESTOURADO"

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime criadoEm;

    // ==== Métodos de domínio ====

    public BigDecimal calcularUsoPercentual() {
        if (limite == null || limite.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return gastoAteAgora
                .multiply(BigDecimal.valueOf(100))
                .divide(limite, 2, RoundingMode.HALF_UP);
    }

    public void ajustarLimite(BigDecimal novoLimite) {
        if (novoLimite == null || novoLimite.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("o novo limite deve ser maior que zero!");
        }
        this.limite = novoLimite;
    }
}
