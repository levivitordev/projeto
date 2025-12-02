package poo.api.projeto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoModel {

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

    // "entrada" ou "saida"
    @Column(nullable = false, length = 20)
    private String tipo;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime criadoEm;

    // === Métodos de domínio ===

    public boolean ehEntrada() {
        return this.tipo != null && this.tipo.equalsIgnoreCase("entrada");
    }

    public boolean ehSaida() {
        return this.tipo != null && this.tipo.equalsIgnoreCase("saida");
    }
}
