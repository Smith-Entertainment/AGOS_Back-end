package AGOS.AGOS.DTO;

import AGOS.AGOS.entity.Mes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoDTO {
    private Long id;
    private Mes mes;
    private int ano;
}
