package AGOS.AGOS.dto;

import AGOS.AGOS.entity.Item;
import AGOS.AGOS.entity.Periodo;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ValorDTO {
    private Long id;
    private BigDecimal previstoFinanceiro;
    private BigDecimal previstoFisico;
    private BigDecimal realizadoFinanceiro;
    private BigDecimal realizadoFisico;
    //----------------------
    private Periodo periodo;
    private Item item;
}
