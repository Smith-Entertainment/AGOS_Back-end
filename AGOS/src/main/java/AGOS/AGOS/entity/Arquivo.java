package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_arquivo", schema = "public")
@AuditTable(value = "tb_arquivo_audit", schema = "audit")
public class Arquivo {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Getter @Setter
    @Column(name = "endereco", nullable = false, length = 50)
    private String endereco;

}
