package AGOS.agos.services;

import AGOS.agos.entity.Documento;
import AGOS.agos.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {
    @Autowired
    DocumentoRepository documentoRepository;

    public Optional<Documento> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O parâmetro não pode ser nulo");
        }
        return documentoRepository.findById(id);
    }

    public List<Documento> findAll() {
        return documentoRepository.findAll();
    }

    public void create(Documento documento) {
        if(documento == null){
            throw new IllegalArgumentException("O parâmetro não pode ser nulo");
        }
        this.documentoRepository.save(documento);
    }

    public void update(Documento documento, Long id) {
        if (documento == null) {
            throw new IllegalArgumentException("O parâmetro não pode ser nulo");
        }
        if (documentoRepository == null || !documentoRepository.findById(id).equals(documento)) {
            throw new RuntimeException("Não foi possível identificar o registro informado");
        }
        this.documentoRepository.save(documento);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O parâmetro não pode ser nulo");
        }
        this.documentoRepository.deleteById(id);
    }
}
