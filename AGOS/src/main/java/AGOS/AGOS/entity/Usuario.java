package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "tb_usuario", schema = "public")
public class Usuario {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "nome", nullable = false)
    private String nome;

    @Getter @Setter
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Getter @Setter
    @Column(name = "celular")
    private String celular;

    @Getter @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNasc;
}
