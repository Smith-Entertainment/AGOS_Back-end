package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Valor", schema = "public")
public class Valor {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
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
    //----------------------
    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "periodo_id")
    private Periodo periodo;
    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "item_id")
    private Item item;
}
