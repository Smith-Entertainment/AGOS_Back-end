package AGOS.AGOS.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
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
    private BigDecimal valorEdital;

    @ManyToOne
    @JoinColumn(name= "bairro",nullable = false)
    private Bairro bairro;

    @Column(name= "rua",length = 30,nullable = false)
    private String rua;

    @Column(name= "numero",nullable = false)
    private BigInteger numeroEndereco;

    @ManyToMany(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private List<Usuario> voluntarios;

    @ManyToOne(fetch =FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "foto")
    private Envio foto;

    @Column(name= "valor_contratado")
    private BigDecimal valorContratado;

    @Column(name= "data_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @Column(name= "data_termino")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataTermino;

    @Column(name= "numero_contrato")
    private int numeroContrato;

    @OneToOne
    @JoinColumn(name= "empresa_contratada")
    private Empresa empresaContratada;

    @Column(name= "situacao", nullable = false)
    private Situacao situacao;

    @Enumerated(EnumType.STRING)
    @Column(name= "tipo_obra")
    private TipoObra tipoObra;


}
