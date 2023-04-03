package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Documento", schema = "public")
public class Documento {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "grupo_projeto")
    private GrupoProjeto grupoProjeto;
    @Getter @Setter
    @OneToMany
    @JoinTable(name = "arquivos")
    private List<Arquivo> arquivos;
    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "obra")
    private Obra obra = new Obra();

}
