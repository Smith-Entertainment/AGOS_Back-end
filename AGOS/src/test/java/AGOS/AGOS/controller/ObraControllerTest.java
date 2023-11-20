package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.ObraDTO;
import AGOS.AGOS.services.ObraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ObraControllerTest {

    @InjectMocks
    private ObraController controller;

    @Mock
    private ObraService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        Long obraId = 1L;
        ObraDTO obraDTO = new ObraDTO();

        when(service.findById(obraId)).thenReturn(obraDTO);

        ResponseEntity<?> response = controller.findById(obraId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(obraDTO, response.getBody());

        verify(service, times(1)).findById(obraId);
    }

    @Test
    void testFindByIdNotFound() {
        Long obraId = 1L;

        when(service.findById(obraId)).thenThrow(new IllegalArgumentException("Obra não encontrada"));

        ResponseEntity<?> response = controller.findById(obraId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Obra não encontrada", response.getBody());

        verify(service, times(1)).findById(obraId);
    }

    @Test
    void testFindAll() {
        List<ObraDTO> obrasDTO = new ArrayList<>();
        obrasDTO.add(new ObraDTO());
        obrasDTO.add(new ObraDTO());

        when(service.findAll()).thenReturn(obrasDTO);

        ResponseEntity<?> response = controller.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(obrasDTO, response.getBody());

        verify(service, times(1)).findAll();
    }

    @Test
    void testCadastrar() {
        ObraDTO obraDTO = new ObraDTO();

        ResponseEntity<?> response = controller.create(obraDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Obra cadastrada com sucesso!", response.getBody());

        verify(service, times(1)).create(obraDTO);
    }

    @Test
    void testEditar() {
        Long obraId = 1L;
        ObraDTO obraDTO = new ObraDTO();

        ResponseEntity<?> response = controller.update(obraId, obraDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Obra editada com sucesso!", response.getBody());

        verify(service, times(1)).update(obraId, obraDTO);
    }

    @Test
    void testExcluir() {
        Long obraId = 1L;

        ResponseEntity<?> response = controller.delete(obraId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Obra excluída com sucesso!", response.getBody());

        verify(service, times(1)).delete(obraId);
    }
}
