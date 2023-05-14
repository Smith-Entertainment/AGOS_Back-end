package AGOS.AGOS.services;

import AGOS.AGOS.entity.Arquivo;
import AGOS.AGOS.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ArquivoService {
    @Autowired
    ArquivoRepository arquivoRepository;

    public Optional<Arquivo> findById(Long id) {
        return arquivoRepository.findById(id);
    }

    public List<Arquivo> findAll() {
        return arquivoRepository.findAll();
    }
    public void create(Arquivo arquivo){
        if (arquivo == null) {
            throw new IllegalArgumentException("O parâmetro 'arquivo' não pode ser nulo");
        }
        this.arquivoRepository.save(arquivo);
    };

    public void update(Arquivo arquivo, Long id) {
        if (arquivo == null) {
            throw new IllegalArgumentException("O parâmetro 'arquivo' não pode ser nulo");
        }
        this.arquivoRepository.findById(id).orElse(null);
        if(arquivoRepository == null || !arquivoRepository.getId(id).equals(arquivo.getId()))
        {
            throw new RuntimeException("Não foi possível identificar o registro informado");
        }
    }

    public void delete(Arquivo arquivo, Long id) {
        if (arquivo == null) {
            throw new IllegalArgumentException("O parâmetro 'arquivo' não pode ser nulo");
        }
        this.arquivoRepository.findById(id).orElse(null);
        if(arquivoRepository == null || !arquivoRepository.getId(id).equals(arquivo.getId()))
        {
            throw new RuntimeException("Não foi possível identificar o registro informado");
        }
    }
}
