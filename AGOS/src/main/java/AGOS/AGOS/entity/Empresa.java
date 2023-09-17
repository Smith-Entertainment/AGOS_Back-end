package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity

@Table(name = "tb_empresa", schema = "public")
public class Empresa {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    Long id;

    @Getter @Setter
    @Column (name = "nome", length = 40, nullable = false)
    String nome;

    @Getter @Setter
    @Column (name = "cnpj", length = 14)
    String cnpj;

}
