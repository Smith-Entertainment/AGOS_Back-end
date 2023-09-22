package AGOS.AGOS.DTO;

import AGOS.AGOS.entity.Meses;
import lombok.*;

@Getter @Setter @Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoDTO {
    private Long id;
    private Meses mes;
    private int ano;
}
