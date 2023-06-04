package AGOS.AGOS.services;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.repository.CronogramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CronogramaService {

    @Autowired
    private CronogramaRepository cronogramaRepository;


    @Transactional
    public Cronograma newCronograma(Cronograma cronograma) {


        return cronogramaRepository.save(cronograma);
    }










}