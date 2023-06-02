package AGOS.AGOS.services;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.entity.Item;
import AGOS.AGOS.entity.Periodo;
import AGOS.AGOS.repository.CronogramaRepository;
import AGOS.AGOS.repository.ItemRepository;
import AGOS.AGOS.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class CronogramaService {

    @Autowired
    private CronogramaRepository cronogramaRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PeriodoRepository periodoRepository;

    @Transactional
    public Cronograma newCronograma(Cronograma cronograma) {
        Item item = itemRepository.findById(cronograma.getItem().getId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        Periodo periodo = periodoRepository.findById(cronograma.getPeriodo().getId())
                .orElseThrow(() -> new IllegalArgumentException("Periodo not found"));

        if (cronograma.getValorContrato() == null) {
            throw new IllegalArgumentException("O valor total do contrato não pode ser nulo");
        }

        if (cronograma.getPrevistoFinanceiro().compareTo(BigDecimal.ZERO) < 0 ||
                cronograma.getPrevistoFisico().compareTo(BigDecimal.ZERO) < 0 ||
                cronograma.getRealizadoFinanceiro().compareTo(BigDecimal.ZERO) < 0 ||
                cronograma.getRealizadoFisico().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Os valores financeiros e físicos não podem ser negativos");
        }

        if (cronograma.getRealizadoFinanceiro().compareTo(cronograma.getPrevistoFinanceiro()) > 0) {
            throw new IllegalArgumentException("O valor financeiro realizado não pode ser maior que o valor financeiro previsto");
        }

        if (cronograma.getRealizadoFisico().compareTo(cronograma.getPrevistoFisico()) > 0) {
            throw new IllegalArgumentException("O valor físico realizado não pode ser maior que o valor físico previsto");
        }

        cronograma.setItem(item);
        cronograma.setPeriodo(periodo);

        return cronogramaRepository.save(cronograma);
    }










}