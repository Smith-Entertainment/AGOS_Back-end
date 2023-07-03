package AGOS.AGOS.services;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.entity.Periodo;
import AGOS.AGOS.repository.CronogramaRepository;
import AGOS.AGOS.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CronogramaService {

    @Autowired
    private CronogramaRepository cronogramaRepository;
    @Autowired
    private PeriodoRepository periodoRepository;


    @Transactional
    public Cronograma newCronograma(Cronograma cronograma) {


        return cronogramaRepository.save(cronograma);
    }










}