package AGOS.AGOS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "Obra", schema = "public")
public class Obra {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    Long id;
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
    @Column(name= "dataCertame",nullable = false)
    private LocalDate data_certame;
    @Getter @Setter
    @Column(name= "valorEdital",nullable = false)
    private double valor_edital;
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
    @Column(name= "valorContratado",length = 50)
    private double valor_contratado;
    @Getter @Setter
    @Column(name= "dataInicio")
    private LocalDate data_inicio;
    @Getter @Setter
    @Column(name= "dataTermino")
    private LocalDate data_termino;
    @Getter @Setter
    @Column(name= "numeroContrato")
    private int numero_contrato;
    @Getter @Setter
    @Column(name= "empresaContratada",length = 30)
    private String empresa_contratada;
    @Getter @Setter
    @Column(name= "finalizado")
    private boolean finalizado;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name= "tipoObra")
    private Tipo_obra tipo;
}
