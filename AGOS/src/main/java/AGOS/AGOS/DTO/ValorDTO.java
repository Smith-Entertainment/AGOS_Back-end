package AGOS.AGOS.DTO;

import AGOS.AGOS.entity.Item;
import AGOS.AGOS.entity.Periodo;
import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @Data
@NoArgsConstructor @AllArgsConstructor
public class ValorDTO {
    private Long id;
    private BigDecimal previstoFinanceiro;
    private BigDecimal previstoFisico;
    private BigDecimal realizadoFinanceiro;
    private BigDecimal realizadoFisico;
    private Periodo periodo;
    private Item item;
}
