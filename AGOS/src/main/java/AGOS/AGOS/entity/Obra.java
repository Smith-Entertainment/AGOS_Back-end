package AGOS.AGOS.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter @Setter
@Table(name = "tb_obra", schema = "public")
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;

    @Column(name= "titulo",length = 120,nullable = false, unique = true)
    private String titulo;

    @Column(name= "cep",length = 9,nullable = false, unique = false)
    private String cep;

    @Column(name= "licitacao",length = 50, unique = true)
    private String licitacao;

    @Column(name= "data_certame")
    private LocalDate dataCertame;

    @Column(name= "valor_edital",nullable = false)
    private double valorEdital;

    @Column(name= "bairro",length = 50,nullable = false)
    private String bairro;

    @Column(name= "rua",length = 30,nullable = false)
    private String rua;

    @Column(name= "numero",nullable = false)
    private int numeroEndereco;

    @ManyToMany(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private List<Usuario> voluntarios;

    @ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "foto")
    private Envio foto;

    @Column(name= "valor_contratado",length = 50)
    private double valorContratado;

    @Column(name= "data_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @Column(name= "data_termino")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataTermino;

    @Column(name= "numero_contrato")
    private int numeroContrato;

    @Column(name= "empresa_contratada",length = 30)
    private String empresaContratada;

    @Column(name= "situacao")
    private Situacao situacao;

    @Enumerated(EnumType.STRING)
    @Column(name= "tipo_obra")
    private TipoObra tipoObra;


}
