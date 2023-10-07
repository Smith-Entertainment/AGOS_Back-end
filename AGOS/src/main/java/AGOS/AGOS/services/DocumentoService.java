package AGOS.AGOS.services;

import AGOS.AGOS.DTO.DocumentoDTO;
import AGOS.AGOS.entity.Documento;
import AGOS.AGOS.repository.DocumentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentoService {
    @Autowired
    DocumentoRepository documentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(rollbackFor = Exception.class)
    public DocumentoDTO findById(Long id) {
        final Documento documento = this.documentoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("O parâmetro não pode ser nulo"));
        return convertToDTO(documento);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<DocumentoDTO> findAll() {
        final List<Documento> documentos= this.documentoRepository.findAll();
        return documentos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public DocumentoDTO create(DocumentoDTO documentoDTO) {
        validateDocumentoDTO(documentoDTO);
        Documento documento = convertToEntity(documentoDTO);
        Optional<Documento> documentoAtual = documentoRepository.findByNome(documento.getNome());
        if (documentoAtual.isPresent() && !documentoAtual.get().getId().equals(documento.getId())) {
            throw new IllegalArgumentException("Já existe um item cadastrado com esse nome: " + documento.getNome());
        }
        documento = documentoRepository.save(documento);
        return convertToDTO(documento);
    }

    @Transactional(rollbackFor = Exception.class)
    public DocumentoDTO update(DocumentoDTO documentoDTO) {
        validateDocumentoDTO(documentoDTO);
        Documento documento = convertToEntity(documentoDTO);
        Optional<Documento> documentoAtual = documentoRepository.findByNome(documento.getNome());
        if (documentoAtual.isPresent() && !documentoAtual.get().getId().equals(documento.getId())) {
            throw new IllegalArgumentException("Já existe um item cadastrado com esse nome: " + documento.getNome());
        }
        documento = documentoRepository.save(documento);
        return convertToDTO(documento);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        final Documento documento = this.documentoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("O parâmetro não pode ser nulo"));

        this.documentoRepository.delete(documento);
    }

    private DocumentoDTO convertToDTO(Documento documento) {
        return modelMapper.map(documento, DocumentoDTO.class);
    }

    private Documento convertToEntity(DocumentoDTO documentoDTO) {
        return modelMapper.map(documentoDTO, Documento.class);
    }

    private void validateDocumentoDTO(DocumentoDTO documentoDTO) {
        if(!documentoDTO.getNome().matches("[a-zA-Z\\sç´~`^-]+")|| documentoDTO.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do item não pode estar em branco");
        }else if(documentoDTO.getGrupoProjeto() == null) {
            throw new IllegalArgumentException("O campo 'GrupoProjeto' não pode ser nulo!");
        }else if (documentoDTO.getObra() == null) {
            throw new IllegalArgumentException("O campo 'Obra' não pode ser nulo!");
        }
    }
}
