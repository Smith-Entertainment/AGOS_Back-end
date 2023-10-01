package AGOS.agos.controller;

import AGOS.agos.dto.ItemDTO;
import AGOS.agos.services.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemControllerTest {

    @InjectMocks
    private ItemController controller;

    @Mock
    private ItemService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void TestControllerFindById01() {
        Long id = 1L;
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(id);

        when(service.findById(id)).thenReturn(itemDTO);

        ResponseEntity<ItemDTO> response = controller.findById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(itemDTO, response.getBody());
    }

    @Test
    void TestControllerCreate01() {
        ItemDTO itemDTO = new ItemDTO();

        when(service.create(itemDTO)).thenReturn(itemDTO);

        ResponseEntity<String> response = controller.create(itemDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cadastrado com sucesso!", response.getBody());
    }

    @Test
    void TestControllerCreate02() {
        ItemDTO itemDTO = new ItemDTO();
        when(service.create(itemDTO)).thenThrow(new IllegalArgumentException("Nome do item não pode estar em branco"));

        ResponseEntity<String> response = controller.create(itemDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void TestControllerUpdate01() {
        Long id = 1L;
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(id);

        when(service.update(itemDTO)).thenReturn(itemDTO);

        ResponseEntity<String> response = controller.update(id, itemDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Editado com sucesso!", response.getBody());
    }

    @Test
    void TestControllerUpdate02() {
        Long id = 1L;
        ItemDTO itemDTO = new ItemDTO();

        when(service.update(itemDTO)).thenThrow(new IllegalArgumentException("Nome do item não pode estar em branco"));

        ResponseEntity<String> response = controller.update(id, itemDTO);

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
