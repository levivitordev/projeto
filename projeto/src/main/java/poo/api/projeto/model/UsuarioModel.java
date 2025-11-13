package poo.api.projeto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")

public class UsuarioModel {
    @Id
    @GeneratedValue
    long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private Integer pontos = 0;

    @CreationTimestamp

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    // ESSA PARTE AQUI TA COMENTADA PORQUE AINDA FALTA LUAN, ALEXIS E GABRIEL FAZER AS OUTRAS CLASSES :

    //@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<CentroDeCustoModel> centroDeCusto = new ArrayList<>();

    //@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<OrcamentoMensalModel> orcamentosMensais = new ArrayList<>();

    //@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<DesafioModel> desafios = new ArrayList<>();

    //@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<MetaModel> metas = new ArrayList<>();

    //@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<TransacaoModel> transacoes = new ArrayList<>();

    public void atualizarPerfil(String nome, Integer idade) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
        if (idade != null && idade > 0) {
            this.idade = idade;
        }
    }

    public void acumularPontos(Integer qtd) {
        if (qtd != null && qtd > 0) {
            this.pontos += qtd;
        }
    }
}
