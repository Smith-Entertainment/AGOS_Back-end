package AGOS.agos.controller;

import AGOS.agos.dto.PeriodoDTO;
import AGOS.agos.services.PeriodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PeriodoControllerTest {
    @InjectMocks
    private PeriodoController controller;
    @Mock
    private PeriodoService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void TestControllerFindById01() {
        Long id = 1L;
        PeriodoDTO periodoDTO = new PeriodoDTO();
        periodoDTO.setId(id);

        when(service.findById(id)).thenReturn(periodoDTO);

        ResponseEntity<PeriodoDTO> response = controller.findById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(periodoDTO, response.getBody());
    }

    @Test
    void TestControllerCreate01() {
        PeriodoDTO periodoDTO = new PeriodoDTO();

        when(service.create(periodoDTO)).thenReturn(periodoDTO);

        ResponseEntity<String> response = controller.create(periodoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cadastrado com sucesso!", response.getBody());
    }

    @Test
    void TestControllerCreate02() {
        PeriodoDTO periodoDTO = new PeriodoDTO();
        when(service.create(periodoDTO)).thenThrow(new IllegalArgumentException("Ano inválido"));

        ResponseEntity<String> response = controller.create(periodoDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void TestControllerUpdate01() {
        Long id = 1L;
        PeriodoDTO periodoDTO = new PeriodoDTO();
        periodoDTO.setId(id);

        when(service.update(periodoDTO)).thenReturn(periodoDTO);

        ResponseEntity<String> response = controller.update(id, periodoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Editado com sucesso!", response.getBody());
    }

    @Test
    void TestControllerUpdate02() {
        Long id = 1L;
        PeriodoDTO periodoDTO = new PeriodoDTO();

        when(service.update(periodoDTO)).thenThrow(new IllegalArgumentException("Ano inválido"));

        ResponseEntity<String> response = controller.update(id, periodoDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void TestControllerDelete01() {
        Long id = 1L;

        ResponseEntity<String> response = controller.delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Excluido com sucesso!", response.getBody());
    }


}
