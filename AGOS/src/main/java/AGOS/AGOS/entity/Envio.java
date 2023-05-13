package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.util.List;

@Entity
@Audited
@Table(name = "tb_envio", schema = "public")
@AuditTable(value = "tb_envio_audit", schema = "audit")
public class Envio {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Getter @Setter
    @ManyToMany(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private List<Usuario> voluntarios;

    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(name = "item", nullable = false)
    private List<Item> item;

    @Getter @Setter
    @ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_obra", nullable = false)
    private Obra obra;
}
