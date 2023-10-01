package AGOS.agos.controller;

import AGOS.agos.dto.ValorDTO;
import AGOS.agos.services.ValorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ValorControllerTest {

    @InjectMocks
    private ValorController controller;

    @Mock
    private ValorService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void TestControllerFindById01() {
        Long id = 1L;
        ValorDTO valorDTO = new ValorDTO();
        valorDTO.setId(id);

        when(service.findById(id)).thenReturn(valorDTO);

        ResponseEntity<ValorDTO> response = controller.findById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(valorDTO, response.getBody());
    }

    @Test
    void TestControllerCreate01() {
        ValorDTO valorDTO = new ValorDTO();

        when(service.create(valorDTO)).thenReturn(valorDTO);

        ResponseEntity<String> response = controller.create(valorDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cadastrado com sucesso!", response.getBody());
    }

    @Test
    void TestControllerCreate02() {
        ValorDTO valorDTO = new ValorDTO();
        when(service.create(valorDTO)).thenThrow(new IllegalArgumentException("Item inválido!"));

        ResponseEntity<String> response = controller.create(valorDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void TestControllerUpdate01() {
        Long id = 1L;
        ValorDTO valorDTO = new ValorDTO();
        valorDTO.setId(id);

        when(service.update(valorDTO)).thenReturn(valorDTO);

        ResponseEntity<String> response = controller.update(id, valorDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Editado com sucesso!", response.getBody());
    }

    @Test
    void TestControllerUpdate02() {
        Long id = 1L;
        ValorDTO valorDTO = new ValorDTO();

        when(service.update(valorDTO)).thenThrow(new IllegalArgumentException("Valores não encontrado"));

        ResponseEntity<String> response = controller.update(id, valorDTO);

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
