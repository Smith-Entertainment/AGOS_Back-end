package AGOS.AGOS.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;

@Entity
@Table(name = "tb_cronograma", schema = "public")
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column (name = "Mes")
    @Enumerated(EnumType.STRING)
    private Meses mes;

    @Getter @Setter
    @Column (name = "ano", length = 4)
    private int ano;

    @Getter @Setter
    @ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.MERGE)
    @JoinColumn(name = "obra_Id")
    private Obra obra;

    @Getter @Setter
    @Column(name = "Item", length = 50)
    private String item;

    @Getter @Setter
    @Column(name = "previsto_financeiro")
    private BigDecimal previstoFinanceiro;

    @Getter @Setter
    @Column(name = "previsto_fisico")
    private BigDecimal previstoFisico;

    @Getter @Setter
    @Column(name = "realizado_financeiro")
    private BigDecimal realizadoFinanceiro;

    @Getter @Setter
    @Column(name = "realizado_fisico")
    private BigDecimal realizadoFisico;

    @Getter @Setter
    @Column(name = "Valor_Contrato", nullable = false)
    private BigDecimal valorContrato;
}