package AGOS.AGOS.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class ObraDTO {
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    private String titulo;

    @Getter @Setter
    private String imagem;

    @Getter @Setter
    private String objetivo;

    @Getter @Setter
    private String licitacao;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataCertame;

    @Getter @Setter
    private double valorEdital;

    @Getter @Setter
    private String bairro;

    @Getter @Setter
    private String rua;

    @Getter @Setter
    private int numero;

   // @Getter @Setter
   // private List<UsuarioDTO> voluntarios;

    @Getter @Setter
    private double valorContratado;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataTermino;

    @Getter @Setter
    private int numeroContrato;

    @Getter @Setter
    private String empresaContratada;

    @Getter @Setter
    private boolean finalizado;

    @Getter @Setter
    private String tipoObra;
}