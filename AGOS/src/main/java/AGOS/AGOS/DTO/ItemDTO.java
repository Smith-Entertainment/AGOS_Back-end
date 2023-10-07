package AGOS.AGOS.DTO;

import AGOS.AGOS.entity.Obra;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String nome;
    private float valorTotal;
    private Obra obra;

    public ItemDTO() {}
}
