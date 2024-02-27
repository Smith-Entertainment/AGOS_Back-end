package AGOS.AGOS.services;

import AGOS.AGOS.DTO.ObraDTO;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.repository.ObraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObraService {
    @Autowired
    private ObraRepository obraRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ObraDTO convertToDTO(Obra obra) {
        return modelMapper.map(obra, ObraDTO.class);
    }

    private Obra convertToEntity(ObraDTO obraDTO) {
        return modelMapper.map(obraDTO, Obra.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public ObraDTO findById(Long obraId) {
        Obra obra = obraRepository.findById(obraId)
                .orElseThrow(() -> new IllegalArgumentException("Obra não encontrada"));
        return convertToDTO(obra);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ObraDTO> findAll() {
        List<Obra> obras = obraRepository.findAll();
        return obras.stream().map(obra -> modelMapper.map(obra, ObraDTO.class)).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(ObraDTO obraDTO) {
        validateObraDTO(obraDTO);
        Obra obra = convertToEntity(obraDTO);
        obraRepository.save(obra);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Long obraId, ObraDTO obraDTO) {
        validateObraDTO(obraDTO);
        Obra existingObra = obraRepository.findById(obraId)
                .orElseThrow(() -> new IllegalArgumentException("Obra não encontrada"));
        modelMapper.map(obraDTO, existingObra);
        obraRepository.save(existingObra);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long obraId) {
        Obra obra = obraRepository.findById(obraId)
                .orElseThrow(() -> new IllegalArgumentException("Obra não encontrada"));
        obraRepository.delete(obra);
    }

    private void assertNotBlank(String value, String message) {
        assert !value.isBlank() : message;
    }

    private void validateObraDTO(ObraDTO obraDTO) {
        if (obraDTO.getTitulo() == null || obraDTO.getTitulo().isBlank()) {
            throw new IllegalArgumentException("Título não pode estar em branco");
        }
        if (obraDTO.getBairro() == null ) {
            throw new IllegalArgumentException("Bairro não pode ser nulo");
        }
        if (obraDTO.getRua() == null || obraDTO.getRua().isBlank()) {
            throw new IllegalArgumentException("Rua não pode estar em branco");
        }
        if (obraDTO.getLicitacao() == null || obraDTO.getLicitacao().isBlank()) {
            throw new IllegalArgumentException("Licitacao não pode estar em branco");
        }
        if (obraDTO.getValorEdital() == null) {
            throw new IllegalArgumentException("Valor do Edital não pode ser nulo");
        }

    }
}
