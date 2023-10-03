package AGOS.AGOS.DTO;
import AGOS.AGOS.entity.Envio;
import AGOS.AGOS.entity.Situacao;

import AGOS.AGOS.entity.TipoObra;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ObraDTO {
    @Setter
    private Long id;

    @Setter
    private String titulo;

    @Setter
    private String imagem;

    @Setter
    private String objetivo;

    @Setter
    private String licitacao;

    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataCertame;

    @Setter
    private double valorEdital;

    @Setter
    private String bairro;

    @Setter
    private String rua;

    @Setter
    private int numero;

    // @Getter @Setter
    // private List<UsuarioDTO> voluntarios;

    @Setter
    private double valorContratado;

    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataTermino;

    @Setter
    private int numeroContrato;

    @Setter
    private String empresaContratada;

    @Setter
    private boolean finalizado;

    @Enumerated(EnumType.STRING)
    @Setter
    private TipoObra tipoObra;

    @Enumerated(EnumType.STRING)
    @Setter
    private Situacao situacao;

    @Setter
    private Envio foto;

    @Setter
    private String cep;


}