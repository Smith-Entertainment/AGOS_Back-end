package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.util.Date;
import java.util.List;


@Entity
@Audited
@Table(name = "tb_usuario", schema = "public")
@AuditTable(value = "tb_usuario_audit", schema = "audit")
public class Usuario {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Getter @Setter
    @Column(name= "usuario",length = 20,nullable = false, unique = true)
    private String usuario;

    @Getter @Setter
    @Column(name= "email",length = 50,nullable = false, unique = true)
    private String email;

    @Getter @Setter
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Getter @Setter
    @Column(name = "celular", length = 20, unique = true, nullable = false)
    private String celular;

    @Getter @Setter
    @Column(name= "titulo_eleitor",nullable = false, unique = true)
    private int tituloEleitor;

    @Getter @Setter
    @Column(name= "senha",length = 20,nullable = false, unique = true)
    private String senha;

    @Getter @Setter
    @Column(name = "nome_pai", length = 50)
    private String nomePai;

    @Getter @Setter
    @Column(name = "nome_mae", length = 50, nullable = false)
    private String nomeMae;

    @Getter @Setter
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Getter @Setter
    @ManyToMany(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_obra")
    private List<Obra> obras;
}
