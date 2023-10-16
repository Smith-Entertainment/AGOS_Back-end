package AGOS.AGOS.services;

import AGOS.AGOS.entity.Documento;
import AGOS.AGOS.entity.GrupoProjeto;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.repository.DocumentoRepository;
import AGOS.AGOS.DTO.DocumentoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DocumentoServiceTest {

    @Mock
    @Autowired
    private DocumentoRepository documentoRepository;

    @Mock
    @Autowired
    private ModelMapper modelMapper;

    @InjectMocks
    private DocumentoService documentoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
    }

    @Test
    public void testCreateDocumento() {
        DocumentoDTO documentoDTO = new DocumentoDTO();
        documentoDTO.setNome("Nome do Documento");
        documentoDTO.setEndereco("1234567890");
        documentoDTO.setGrupoProjeto(GrupoProjeto.HIDRAULICO);
        documentoDTO.setObra(new Obra());


        Documento documento = new Documento();
        documento.setNome(documentoDTO.getNome());
        documento.setEndereco(documentoDTO.getEndereco());
        documento.setGrupoProjeto(documentoDTO.getGrupoProjeto());
        documento.setObra(documentoDTO.getObra());

        when(documentoRepository.save(any(Documento.class))).thenReturn(documento);

        DocumentoDTO result = documentoService.create(documentoDTO);

        assertNotNull(result);
        assertEquals(documentoDTO.getNome(), result.getNome());
        assertEquals(documentoDTO.getEndereco(), result.getEndereco());
        assertEquals(documentoDTO.getGrupoProjeto(), result.getGrupoProjeto());


        verify(documentoRepository, times(1)).save(any(Documento.class));
    }

    @Test
    public void testUpdateDocumento() {
        Long id = 1L;
        DocumentoDTO documentoDTO = new DocumentoDTO();
        documentoDTO.setNome("Nome Atualizado");
        documentoDTO.setEndereco("9876543210");
        documentoDTO.setGrupoProjeto(GrupoProjeto.HIDRAULICO);
        documentoDTO.setObra(new Obra());


        Documento documentoExistente = new Documento();
        documentoExistente.setId(id);
        documentoExistente.setNome("Nome Antigo");
        documentoExistente.setEndereco("1234567890");
        documentoExistente.setGrupoProjeto(GrupoProjeto.HIDRAULICO);
        documentoExistente.setObra(new Obra());


        when(documentoRepository.findById(id)).thenReturn(Optional.of(documentoExistente));
        when(documentoRepository.save(any(Documento.class))).thenReturn(documentoExistente);

        DocumentoDTO result = documentoService.update(id, documentoDTO);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(documentoDTO.getNome(), result.getNome());
        assertEquals(documentoDTO.getEndereco(), result.getEndereco());
        assertEquals(documentoDTO.getGrupoProjeto(), result.getGrupoProjeto());
        assertEquals(documentoDTO.getObra(), result.getObra());

        verify(documentoRepository, times(1)).findById(id);
        verify(documentoRepository, times(1)).save(any(Documento.class));
    }

    @Test
    public void testDeleteDocumento() {
        Long id = 1L;

        documentoService.delete(id);

        verify(documentoRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindAllDocumentos() {
        List<Documento> documentos = Arrays.asList(new Documento(), new Documento());

        when(documentoRepository.findAll()).thenReturn(documentos);

        List<DocumentoDTO> result = documentoService.findAll();

        assertNotNull(result);
        assertEquals(documentos.size(), result.size());

        verify(documentoRepository, times(1)).findAll();
    }

    @Test
    public void testGetDocumentoById() {
        Long id = 1L;
        Documento documento = new Documento();
        documento.setId(id);

        when(documentoRepository.findById(id)).thenReturn(Optional.of(documento));

        DocumentoDTO result = documentoService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());

        verify(documentoRepository, times(1)).findById(id);
    }

}
