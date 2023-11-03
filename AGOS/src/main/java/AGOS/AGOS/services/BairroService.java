package AGOS.AGOS.services;

import AGOS.AGOS.DTO.BairroDTO;
import AGOS.AGOS.entity.Bairro;
import AGOS.AGOS.repository.BairroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BairroService {
    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(rollbackFor = Exception.class)
    public BairroDTO findById(final Long id){
        Bairro bairro = this.bairroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bairro não encontrado!"));
        return convertToDTO(bairro);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<BairroDTO> findAll(){
        List<Bairro> bairros = this.bairroRepository.findAll();
        if (bairros.isEmpty()){
            throw new IllegalArgumentException("Nenhum bairro encontrado!");
        }
        return bairros.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public BairroDTO create(final BairroDTO bairroDTO){
        Bairro bairroDatabase = this.bairroRepository.findByNome(bairroDTO.getNome());
        if (bairroDatabase != null){
            throw new IllegalArgumentException("Bairro já cadastrado!");
        }

        if (bairroDTO.getNome().isBlank()){
            throw new IllegalArgumentException("Nome de bairro inválido!");
        }

        return convertToDTO(this.bairroRepository.save(convertToEntity(bairroDTO)));
    }

    @Transactional(rollbackFor = Exception.class)
    public BairroDTO update(final Long id, final BairroDTO bairroDTO){
        Bairro bairroDatabase;

        bairroDatabase = this.bairroRepository.findById(id).orElse(null);
        if(bairroDatabase == null){
            throw new IllegalArgumentException("Registro não encontrado");
        }
        if(!bairroDatabase.getId().equals(bairroDTO.getId())){
            throw new IllegalArgumentException("Registros não conferem");
        }

        bairroDatabase = this.bairroRepository.findByNome(bairroDTO.getNome());
        if(bairroDatabase != null && !bairroDatabase.getId().equals(bairroDTO.getId())){
            throw new IllegalArgumentException("Bairro já cadastrado!");
        }

        if (bairroDTO.getNome().isBlank()){
            throw new IllegalArgumentException("Nome de bairro inválido!");
        }

        return convertToDTO(this.bairroRepository.save(convertToEntity(bairroDTO)));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Long id){
        Bairro bairro = this.bairroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bairro não encontrado!"));
        this.bairroRepository.delete(bairro);
    }

    private BairroDTO convertToDTO(Bairro bairro){
        BairroDTO bairroDTO = modelMapper.map(bairro, BairroDTO.class);
        return bairroDTO;
    }

    private Bairro convertToEntity(BairroDTO bairroDTO){
        Bairro bairro = modelMapper.map(bairroDTO, Bairro.class);
        return bairro;
    }
}
