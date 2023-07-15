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
    @ManyToOne
    @JoinColumn(name = "Item_id")
    private Item item;

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
    @Column(name = "Valor_Contrato")
    private BigDecimal valorContrato;

    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "periodo_id")
    private Periodo periodo;
}