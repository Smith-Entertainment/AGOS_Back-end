package AGOS.AGOS.DTO;

import AGOS.AGOS.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
@Getter @Setter
public class ObraDTO {

    private Long id;

    private String titulo;

    private String objetivo;

    private String cep;

    private String licitacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataCertame;

    private BigDecimal valorEdital;

    private Bairro bairro;

    private String rua;

    private BigInteger numeroEndereco;

  //  private List<UsuarioDTO> voluntarios;

    private BigDecimal valorContratado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataTermino;

    private int numeroContrato;

    private Empresa empresaContratada;


    private Situacao situacao;
    private TipoObra tipoObra;

    private Envio foto;
}