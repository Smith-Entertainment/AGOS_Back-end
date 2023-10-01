package ObrasJava.ObrasJava;

import AGOS.AGOS.controller.ObraController;
import AGOS.AGOS.dto.ObraDTO;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.repository.ObraRepository;
import AGOS.AGOS.services.ObraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Mock
    private ObraRepository obraRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        Long obraId = 1L;
        Obra obra = new Obra();

        when(obraRepository.findById(obraId)).thenReturn(java.util.Optional.of(obra));

        ResponseEntity<?> response = controller.findById(obraId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(obra, response.getBody());

        verify(obraRepository, times(1)).findById(obraId);
    }

    @Test
    void testFindByIdNotFound() {
        Long obraId = 1L;

        when(obraRepository.findById(obraId)).thenReturn(java.util.Optional.empty());

        ResponseEntity<?> response = controller.findById(obraId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Id não existe ou não foi encontrado", response.getBody());

        verify(obraRepository, times(1)).findById(obraId);
    }

    @Test
    void testFindAll() {
        List<Obra> obras = new ArrayList<>();
        obras.add(new Obra());
        obras.add(new Obra());

        when(obraRepository.findAll()).thenReturn(obras);

        ResponseEntity<?> response = controller.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(obras, response.getBody());

        verify(obraRepository, times(1)).findAll();
    }

    @Test
    void testCadastrar() {
        ObraDTO obraDTO = new ObraDTO();

        ResponseEntity<?> response = controller.cadastrar(obraDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Obra cadastrada com sucesso!", response.getBody());

        verify(service, times(1)).createObra(obraDTO);
    }


    @Test
    void testEditar() {
        Long obraId = 1L;
        ObraDTO obraDTO = new ObraDTO();

        ResponseEntity<?> response = controller.editar(obraId, obraDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Obra editada com sucesso!", response.getBody());

        verify(service, times(1)).updateObra(obraId, obraDTO);
    }



    @Test
    void testExcluir() {
        Long obraId = 1L;

        when(obraRepository.findById(obraId)).thenReturn(java.util.Optional.of(new Obra()));

        ResponseEntity<?> response = controller.excluir(obraId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Obra deletada com sucesso!", response.getBody());

        verify(obraRepository, times(1)).delete(any(Obra.class));
    }

    @Test
    void testExcluirNotFound() {
        Long obraId = 1L;

        when(obraRepository.findById(obraId)).thenReturn(java.util.Optional.empty());

        ResponseEntity<?> response = controller.excluir(obraId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Obra informada não existe ou já foi deletada", response.getBody());

        verify(obraRepository, never()).delete(any(Obra.class));
    }
}
