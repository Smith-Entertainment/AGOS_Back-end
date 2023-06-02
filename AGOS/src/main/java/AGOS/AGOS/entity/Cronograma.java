package AGOS.AGOS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

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
    @Column(name = "Valor_Contrato", nullable = false)
    private BigDecimal valorContrato;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_periodo")
    private Periodo periodo    ;

    @Getter @Setter
    @ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_obra")
    private Obra obra;

    @Getter @Setter
    @ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_item")
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

}