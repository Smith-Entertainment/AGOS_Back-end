package AGOS.AGOS.dto;

import AGOS.AGOS.entity.Envio;
import AGOS.AGOS.entity.Situacao;
import AGOS.AGOS.entity.TipoObra;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
public class ObraDTO {

    private Long id;
    @Getter @Setter
    private String titulo;

    private String cep;

    private String licitacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataCertame;

    private double valorEdital;

    private String bairro;

    private String rua;

    private int numero;

  //  private List<UsuarioDTO> voluntarios;

    private double valorContratado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataTermino;

    private int numeroContrato;

    private String empresaContratada;


    private Situacao situacao;

    @Enumerated(EnumType.STRING)
    private TipoObra tipoObra;

    private Envio foto;
}