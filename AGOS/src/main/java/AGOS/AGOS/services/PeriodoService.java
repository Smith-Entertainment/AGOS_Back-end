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

    @Transactional(rollbackFor = Exception.class)
    public Periodo findById(Long id){
        final Periodo periodo = this.periodoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Periodo não encontrado"));
        return periodo;
    }
    @Transactional(rollbackFor = Exception.class)
    public List<Periodo> findAll(Long id){
        final List<Periodo> periodos = periodoRepository.findByObraId(id);
        return periodos;
    }

    @Transactional(rollbackFor = Exception.class)
    public Periodo create (Periodo periodo){
        int ano = periodo.getAno();
        String anoString = Integer.toString(ano);

        if (!anoString.matches("[0-9]{4}")){
            throw new IllegalArgumentException("Ano inválido");
        }
        return periodoRepository.save(periodo);
    }

    @Transactional(rollbackFor = Exception.class)
    public Periodo update (Periodo periodo){
        int ano = periodo.getAno();
        String anoString = Integer.toString(ano);

        if (!anoString.matches("[0-9]{4}")){
            throw new IllegalArgumentException("Ano inválido");
        }
        return periodoRepository.save(periodo);
    }


}