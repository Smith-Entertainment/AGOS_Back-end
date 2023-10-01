package AGOS.AGOS.services;

import AGOS.AGOS.entity.*;
import AGOS.AGOS.repository.ObraRepository;
import AGOS.AGOS.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ObraService {


    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private ObraRepository obraRepository;

   /public void atualizarPeriodosObra(Obra obra) {
        final Obra obraId = obraRepository.findById(obra.getId()).orElse(null);
        LocalDate dataInicio = obra.getDataInicio();
        LocalDate dataTermino = obra.getDataTermino();


        List<Periodo> periodos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("pt", "BR"));

        YearMonth mesAnoInicio = YearMonth.from(dataInicio);
        YearMonth mesAnoTermino = YearMonth.from(dataTermino);

        while (!mesAnoInicio.isAfter(mesAnoTermino)) {
            Periodo periodo = new Periodo();
            periodo.setMes(Meses.valueOf(mesAnoInicio.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).toUpperCase()));
            periodo.setAno(mesAnoInicio.getYear());
            periodo.setObra(obraId);
            periodos.add(periodo);
            periodoRepository.save(periodo);
            mesAnoInicio = mesAnoInicio.plusMonths(1);
        }   
        obraRepository.save(obra);
    }

}
