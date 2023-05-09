package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Audited
@Table(name = "tb_documento", schema = "public")
@AuditTable(value = "tb_documento_audit", schema = "audit")
public class Documento {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "grupo_projeto", nullable = false)
    private GrupoProjeto grupoProjeto;

    @Getter @Setter
    @OneToMany(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "arquivos", nullable = false)
    private List<Arquivo> arquivos;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_obra", nullable = false)
    private Obra obra;
}
