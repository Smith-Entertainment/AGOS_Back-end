package AGOS.AGOS.services;

import AGOS.AGOS.entity.Arquivo;
import AGOS.AGOS.repository.ArquivoRepository;
import org.hibernate.envers.Audited;
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
}
