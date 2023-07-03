package AGOS.AGOS.services;

import AGOS.AGOS.entity.Periodo;
import AGOS.AGOS.repository.CronogramaRepository;
import AGOS.AGOS.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeriodoService {

    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private CronogramaRepository cronogramaRepository;

    @Transactional
    public Periodo newPeriodo (Periodo periodo){
        int ano = periodo.getAno();
        String anoString = Integer.toString(ano);

        if (!anoString.matches("[0-9]{4}")){
            throw new IllegalArgumentException("Ano inválido");
        }
        return periodoRepository.save(periodo);
    }

    @Transactional
    public Periodo updatePeriodo (Periodo periodo){
        int ano = periodo.getAno();
        String anoString = Integer.toString(ano);

        if (!anoString.matches("[0-9]{4}")){
            throw new IllegalArgumentException("Ano inválido");
        }
        return periodoRepository.save(periodo);
    }


}