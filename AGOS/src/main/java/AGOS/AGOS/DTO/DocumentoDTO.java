package AGOS.AGOS.DTO;

import AGOS.AGOS.entity.GrupoProjeto;
import AGOS.AGOS.entity.Obra;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentoDTO {

    private Long id;

    private String nome;

    private String endereco;

    private GrupoProjeto grupoProjeto;

    private Obra obra;


    public DocumentoDTO(long id, String documento, String endereco) {
    }

    public DocumentoDTO() {

    }

    public DocumentoDTO(long id, String s) {
    }
}
