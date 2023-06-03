package AGOS.AGOS.services;

import AGOS.AGOS.entity.*;
import AGOS.AGOS.repository.CronogramaRepository;
import AGOS.AGOS.repository.ItemRepository;
import AGOS.AGOS.repository.ObraRepository;
import AGOS.AGOS.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CronogramaService {

    @Autowired
    private CronogramaRepository cronogramaRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private ObraRepository obraRepository;

    @Transactional
    public Cronograma newCronograma(Cronograma cronograma) {


        return cronogramaRepository.save(cronograma);
    }

    public void atualizarPeriodosObra(Obra obra) {
        LocalDate dataInicio = obra.getDataInicio();
        LocalDate dataTermino = obra.getDataTermino();

        List<Periodo> periodos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");

        YearMonth mesAnoInicio = YearMonth.from(dataInicio);
        YearMonth mesAnoTermino = YearMonth.from(dataTermino);

        while (!mesAnoInicio.isAfter(mesAnoTermino)) {
            Periodo periodo = new Periodo();
            periodo.setNomeMes(NomeMes.valueOf(mesAnoInicio.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).toUpperCase()));
            periodo.setAno(mesAnoInicio.getYear());
            periodos.add(periodo);
            periodoRepository.save(periodo);
            mesAnoInicio = mesAnoInicio.plusMonths(1);
        }
    }








}
