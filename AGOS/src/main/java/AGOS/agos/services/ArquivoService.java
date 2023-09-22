package AGOS.agos.services;

import AGOS.agos.entity.Arquivo;
import AGOS.agos.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ArquivoService {
    @Autowired
    ArquivoRepository arquivoRepository;

    public Optional<Arquivo> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O parâmetro não pode ser nulo");
        }
        return arquivoRepository.findById(id);
    }

    public List<Arquivo> findAll() {
        return arquivoRepository.findAll();
    }

    public void create(Arquivo arquivo){
        if (arquivo == null) {
            throw new IllegalArgumentException("O parâmetro não pode ser nulo");
        }
        this.arquivoRepository.save(arquivo);
    };

    public void update(Arquivo arquivo, Long id) {
        if (arquivo == null) {
            throw new IllegalArgumentException("O parâmetro não pode ser nulo");
        }
        if(arquivoRepository == null || !arquivoRepository.findById(id).equals(arquivo))
        {
            throw new RuntimeException("Não foi possível identificar o registro informado");
        }
        this.arquivoRepository.save(arquivo);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O parâmetro não pode ser nulo");
        }
        this.arquivoRepository.deleteById(id);
    }
}
