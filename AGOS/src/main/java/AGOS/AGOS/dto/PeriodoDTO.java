package AGOS.AGOS.dto;

import AGOS.AGOS.entity.Meses;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PeriodoDTO {
    private Long id;
    private Meses mes;
    private int ano;
}
