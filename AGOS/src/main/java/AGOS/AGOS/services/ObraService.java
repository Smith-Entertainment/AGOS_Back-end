package AGOS.AGOS.services;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.entity.NomeMes;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.entity.Periodo;
import AGOS.AGOS.repository.CronogramaRepository;
import AGOS.AGOS.repository.ObraRepository;
import AGOS.AGOS.repository.PeriodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ObraService {

    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private ObraRepository obraRepository;
    @Autowired
    private CronogramaRepository cronogramaRepository;

    public void atualizarPeriodosObra(Obra obra) {
        LocalDate dataInicio = obra.getDataInicio();
        LocalDate dataTermino = obra.getDataTermino();

        List<Periodo> periodos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("pt", "BR"));

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
        obraRepository.save(obra);
    }


}
