package AGOS.AGOS.DTO;

import AGOS.AGOS.entity.Item;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.entity.Usuario;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class EnvioDTO {


    private Long id;
    private Usuario voluntario;
    private String enderecoArquivo;
    private String comentario;
    private ObraDTO obraDTO;



}
