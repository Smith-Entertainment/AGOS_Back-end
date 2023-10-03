package AGOS.AGOS.DTO;

import AGOS.AGOS.entity.Obra;
import lombok.*;


@Getter @Setter @Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String nome;
    private float valorTotal;
    private Obra obra;
}
