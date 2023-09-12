package AGOS.AGOS.services;

import AGOS.AGOS.entity.Item;
import AGOS.AGOS.entity.Periodo;
import AGOS.AGOS.entity.Valor;
import AGOS.AGOS.repository.ItemRepository;
import AGOS.AGOS.repository.PeriodoRepository;
import AGOS.AGOS.repository.ValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ValorService {
    @Autowired
    private ValorRepository valorRepository;
    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Transactional(rollbackFor = Exception.class)
    public Valor findById(final Long id) {
        final Valor cronograma = this.valorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cronograma não encontrada"));
        return cronograma;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Valor> findAll() {
        final List<Valor> cronogramaList = this.valorRepository.findAll();
        return cronogramaList;
    }

    @Transactional(rollbackFor = Exception.class)
    public Valor create(Valor cronograma) {

        final Periodo periodo = this.periodoRepository.findById(cronograma.getPeriodo().getId())
                .orElseThrow(() -> new IllegalArgumentException("Periodo não encontrado"));
        final Item item = this.itemRepository.findById(cronograma.getItem().getId())
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

        if (item == null) {
            throw new IllegalArgumentException("Item deve ser preenxido");
        }
        if (periodo == null) {
            throw new IllegalArgumentException("Periodo deve ser preenxido");
        }
        return valorRepository.save(cronograma);
    }

    @Transactional(rollbackFor = Exception.class)
    public Valor update(Valor cronograma) {

        final Valor validation = this.valorRepository.findById(cronograma.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cronograma não encontrado"));

        final Periodo periodo = this.periodoRepository.findById(cronograma.getPeriodo().getId())
                .orElseThrow(() -> new IllegalArgumentException("Periodo não encontrado"));
        final Item item = this.itemRepository.findById(cronograma.getItem().getId())
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

        if (item == null) {
            throw new IllegalArgumentException("Item deve ser preenxido");
        }
        if (periodo == null) {
            throw new IllegalArgumentException("Periodo deve ser preenxido");
        }
        cronograma.setPrevistoFinanceiro(cronograma.getPrevistoFinanceiro());
        cronograma.setRealizadoFinanceiro(cronograma.getRealizadoFinanceiro());
        cronograma.setPrevistoFisico(cronograma.getPrevistoFisico());
        cronograma.setRealizadoFisico(cronograma.getRealizadoFisico());

        return valorRepository.save(cronograma);
    }
}


