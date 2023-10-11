package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.UsuarioDTO;
import AGOS.AGOS.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UsuarioControllerTest {
    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void TestControllerFindById01() {
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(id);

        when(usuarioService.findById(id)).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = usuarioController.findById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDTO, response.getBody());
    }

    @Test
    void TestControllerCreate01() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        when(usuarioService.create(usuarioDTO)).thenReturn(usuarioDTO);

        ResponseEntity<String> response = usuarioController.create(usuarioDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário cadastrado com sucesso!", response.getBody());
    }

    @Test
    void TestControllerCreate02() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.create(usuarioDTO)).thenThrow(new IllegalArgumentException("Deve conter cpf!"));

        ResponseEntity<String> response = usuarioController.create(usuarioDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void TestControllerUpdate01() {
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(id);

        when(usuarioService.update(1L, usuarioDTO)).thenReturn(usuarioDTO);

        ResponseEntity<String> response = usuarioController.update(id, usuarioDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário editado com sucesso!", response.getBody());
    }

    @Test
    void TestControllerUpdate02() {
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        when(usuarioService.update(1L, usuarioDTO)).thenThrow(new IllegalArgumentException("Nome do item não pode estar em branco"));

        ResponseEntity<String> response = usuarioController.update(id, usuarioDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void TestControllerDelete01() {
        Long id = 1L;

        ResponseEntity<String> response = usuarioController.delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário excluido com sucesso!", response.getBody());
    }
}
