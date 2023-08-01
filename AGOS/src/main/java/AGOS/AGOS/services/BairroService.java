package AGOS.AGOS.services;

import AGOS.AGOS.entity.Bairro;
import AGOS.AGOS.repository.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BairroService {
    @Autowired
    private BairroRepository bairroRepository;

    @Transactional(rollbackFor = Exception.class)
    public Bairro findById(final Long id){
        Bairro bairro = this.bairroRepository.findById(id).orElse(null);
        return bairro;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Bairro> findAll(){
        List<Bairro> bairros = this.bairroRepository.findAll();
        return bairros;
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(final Bairro bairro){
        Bairro bairroDatabase = this.bairroRepository.findByNome(bairro.getNome());
        if (bairroDatabase != null){
            throw new IllegalArgumentException("Bairro já cadastrado!");
        }

        this.bairroRepository.save(bairro);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(final Long id, final Bairro bairro){
        Bairro bairroBanco = findById(id);

        if(bairroBanco == null){
            throw new IllegalArgumentException("Registro não encontrado");
        }
        if(!bairroBanco.getId().equals(bairro.getId())){
            throw new IllegalArgumentException("Registros não conferem");
        }

        Bairro bairroDatabase = this.bairroRepository.findByNome(bairro.getNome());
        if(bairroDatabase != null && !bairroDatabase.getId().equals(bairro.getId())){
            throw new IllegalArgumentException("Bairro já cadastrado!");
        }

        this.bairroRepository.save(bairro);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Long id){
        Bairro bairro = findById(id);
        this.bairroRepository.delete(bairro);
    }
}
