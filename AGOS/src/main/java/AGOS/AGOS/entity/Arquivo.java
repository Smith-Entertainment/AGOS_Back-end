package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Arquivo", schema = "public")
public class Arquivo {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "nome", length = 30)
    private String nome;
    @Getter @Setter
    @Column(name = "endereco", length = 50)
    private String endereco;

}
