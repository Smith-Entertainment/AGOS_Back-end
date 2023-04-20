package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_obra", schema = "public")
public class Obra {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;

    @Getter @Setter
    @Column(name= "titulo",length = 120,nullable = false, unique = true)
    private String titulo;

    @Getter @Setter
    @Column(name= "objetivo",length = 200,nullable = false)
    private String objetivo;

    @Getter @Setter
    @Column(name= "licitacao",length = 50,nullable = false, unique = true)
    private String licitacao;

    @Getter @Setter
    @Column(name= "data_certame",nullable = false)
    private LocalDate dataCertame;

    @Getter @Setter
    @Column(name= "valor_edital",nullable = false)
    private double valorEdital;

    @Getter @Setter
    @Column(name= "bairro",length = 50,nullable = false)
    private String bairro;

    @Getter @Setter
    @Column(name= "rua",length = 30,nullable = false)
    private String rua;

    @Getter @Setter
    @Column(name= "numero",nullable = false)
    private int numero;

    @Getter @Setter
    @OneToMany(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private List<Usuario> voluntarios;

    @Getter @Setter
    @Column(name= "valor_contratado",length = 50)
    private double valorContratado;

    @Getter @Setter
    @Column(name= "data_inicio")
    private LocalDate dataInicio;

    @Getter @Setter
    @Column(name= "data_termino")
    private LocalDate dataTermino;

    @Getter @Setter
    @Column(name= "numero_contrato")
    private int numeroContrato;

    @Getter @Setter
    @Column(name= "empresa_contratada",length = 30)
    private String empresaContratada;

    @Getter @Setter
    @Column(name= "finalizado", nullable = false)
    private boolean finalizado;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name= "tipo_obra", nullable = false)
    private Tipo_obra tipoObra;
}
