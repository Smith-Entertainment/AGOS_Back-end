package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_documento", schema = "public")
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
    @OneToMany(mappedBy = "documento",fetch =FetchType.LAZY ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Arquivo> arquivos = new ArrayList<>();

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "obra", nullable = false)
    private Obra obra;
}
