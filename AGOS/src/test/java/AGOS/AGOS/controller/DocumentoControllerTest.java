package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.DocumentoDTO;
import AGOS.AGOS.services.DocumentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DocumentoControllerTest {

    @InjectMocks
    private DocumentoController controller;


    @Mock
    @Autowired
    private DocumentoService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        Long documentoId = 1L;
        DocumentoDTO documentoDTO = new DocumentoDTO();

        when(service.findById(documentoId)).thenReturn(documentoDTO);

        ResponseEntity<?> response = controller.findById(documentoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(documentoDTO, response.getBody());

        verify(service, times(1)).findById(documentoId);
    }

    @Test
    void testFindAll() {
        List<DocumentoDTO> documentosDTO = new ArrayList<>();
        documentosDTO.add(new DocumentoDTO());
        documentosDTO.add(new DocumentoDTO());

        when(service.findAll()).thenReturn(documentosDTO);

        ResponseEntity<?> response = controller.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(documentosDTO, response.getBody());

        verify(service, times(1)).findAll();
    }

    @Test
    void testCreate() {
        DocumentoDTO documentoDTO = new DocumentoDTO();

        ResponseEntity<?> response = controller.create(documentoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registro cadastrado com sucesso", response.getBody());

        verify(service, times(1)).create(documentoDTO);
    }

    @Test
    void testCreateWithException() {
        DocumentoDTO documentoDTO = new DocumentoDTO();

        when(service.create(documentoDTO)).thenThrow(new RuntimeException("Erro"));

        ResponseEntity<?> response = controller.create(documentoDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro", response.getBody());

        verify(service, times(1)).create(documentoDTO);
    }

    @Test
    void testUpdate() {
        Long documentoId = 1L;
        DocumentoDTO documentoDTO = new DocumentoDTO();

        ResponseEntity<?> response = controller.update(documentoId, documentoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registro editado com sucesso", response.getBody());
        System.out.println("Registro editado com sucesso");

        verify(service, times(1)).update(documentoId, documentoDTO);
    }

    @Test
    void testUpdateWithException() {
        Long documentoId = 1L;
        DocumentoDTO documentoDTO = new DocumentoDTO();

        when(service.update(documentoId, documentoDTO)).thenThrow(new RuntimeException("Erro"));

        ResponseEntity<?> response = controller.update(documentoId, documentoDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro", response.getBody());

        verify(service, times(1)).update(documentoId, documentoDTO);
    }

    @Test
    void testDelete() {
        Long documentoId = 1L;

        ResponseEntity<?> response = controller.delete(documentoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registro excluído com sucesso", response.getBody());

        verify(service, times(1)).delete(documentoId);
    }

}