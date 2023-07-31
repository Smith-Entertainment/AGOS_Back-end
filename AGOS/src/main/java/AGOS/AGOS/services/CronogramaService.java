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
import java.util.List;

@Service
public class CronogramaService {
    @Autowired
    private CronogramaRepository cronogramaRepository;
    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Transactional(rollbackFor = Exception.class)
    public Cronograma findById(final Long id){
        final Cronograma cronograma = this.cronogramaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cronograma não encontrada"));
        return cronograma;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Cronograma> findAll(){
        final List<Cronograma> cronogramaList = this.cronogramaRepository.findAll();
        return cronogramaList;
    }

    @Transactional(rollbackFor = Exception.class)
    public Cronograma create(Cronograma cronograma) {

    final Periodo periodo = this.periodoRepository.findById(cronograma.getPeriodo().getId())
            .orElseThrow(() -> new IllegalArgumentException("Periodo não encontrado"));
    final Item item = this.itemRepository.findById(cronograma.getItem().getId())
            .orElseThrow(()-> new IllegalArgumentException("Item não encontrado"));

    if (item == null ){
        throw new IllegalArgumentException("Item deve ser preenxido");
    }
    if ( periodo == null){
        throw new IllegalArgumentException("Periodo deve ser preenxido");
    }
        return cronogramaRepository.save(cronograma);
    }
    @Transactional(rollbackFor = Exception.class)
    public Cronograma update(Cronograma cronograma) {

        final Cronograma validation = this.cronogramaRepository.findById(cronograma.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cronograma não encontrado"));

        final Periodo periodo = this.periodoRepository.findById(cronograma.getPeriodo().getId())
                .orElseThrow(() -> new IllegalArgumentException("Periodo não encontrado"));
        final Item item = this.itemRepository.findById(cronograma.getItem().getId())
                .orElseThrow(()-> new IllegalArgumentException("Item não encontrado"));

        if (item == null ){
            throw new IllegalArgumentException("Item deve ser preenxido");
        }
        if ( periodo == null){
            throw new IllegalArgumentException("Periodo deve ser preenxido");
        }
        cronograma.setPrevistoFinanceiro(cronograma.getPrevistoFinanceiro());
        cronograma.setRealizadoFinanceiro(cronograma.getRealizadoFinanceiro());
        cronograma.setPrevistoFisico(cronograma.getPrevistoFisico());
        cronograma.setRealizadoFisico(cronograma.getRealizadoFisico());

        return cronogramaRepository.save(cronograma);
    }







}