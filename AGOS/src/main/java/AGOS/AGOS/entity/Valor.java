package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Valor", schema = "public")
public class Valor {
    @Id
    @Getter @Setter
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
    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "periodo_id")
    private Periodo periodo;
    @ManyToOne
    @Getter @Setter
<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "Item_id")
=======
    @JoinColumn(name = "item_id")
>>>>>>> 83c591976b83e26e19e83635319b68d43aeb47ab
    private Item item;
}
