package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "tb_documento", schema = "public")
@NoArgsConstructor
public class Documento {
    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;


    @Setter
    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Setter
    @Column(name = "endereco", nullable = false, length = 50)
    private String endereco;

    @Enumerated(EnumType.STRING)
    @Setter
    @Column(name = "grupo_projeto", nullable = false)
    private GrupoProjeto grupoProjeto;

    @ManyToOne
    @Setter
    @JoinColumn(name = "obra", nullable = false)
    private Obra obra;

    public Documento(long id, String documento1, String endereco) {
    }
}
