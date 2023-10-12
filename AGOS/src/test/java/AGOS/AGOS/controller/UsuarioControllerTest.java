package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.UsuarioDTO;
import AGOS.AGOS.services.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
    void TestControllerFindById01() {   //Certo
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(id);
        when(usuarioService.findById(id)).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = usuarioController.findById(id);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(usuarioDTO, response.getBody());
    }

    @Test
    void TestControllerFindById02() {   //Falha
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.findById(id)).thenThrow(new IllegalArgumentException("Usuário não encontrado!"));

        ResponseEntity<UsuarioDTO> response = usuarioController.findById(id);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNull(response.getBody().getId());
        Assertions.assertNull(response.getBody().getNome());
    }

    @Test
    void TestControllerFindAll(){
        List<UsuarioDTO> lista = List.of(new UsuarioDTO(), new UsuarioDTO(), new UsuarioDTO());
        when(usuarioService.findAll()).thenReturn(lista);

        ResponseEntity<List<UsuarioDTO>> response = usuarioController.findAll();
        int quantidade = response.getBody().size();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(3, quantidade);
    }

    @Test
    void TestControllerCreate01() {  //Certo
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        String mensagem = "Usuário cadastrado com sucesso!";
        when(usuarioService.create(usuarioDTO)).thenReturn(usuarioDTO);

        ResponseEntity<String> response = usuarioController.create(usuarioDTO);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(mensagem, response.getBody());
    }

    @Test
    void TestControllerCreate02() {  //Falha
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        String mensagem = "Deve conter cpf!";
        when(usuarioService.create(usuarioDTO)).thenThrow(new IllegalArgumentException(mensagem));

        ResponseEntity<String> response = usuarioController.create(usuarioDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals(mensagem, response.getBody());
    }

    @Test
    void TestControllerUpdate01() {  //Certo
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(id);
        String mensagem = "Usuário editado com sucesso!";
        when(usuarioService.update(id, usuarioDTO)).thenReturn(usuarioDTO);

        ResponseEntity<String> response = usuarioController.update(id, usuarioDTO);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(mensagem, response.getBody());
    }

    @Test
    void TestControllerUpdate02() {  //Falha
        Long id = 2L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(id);
        String mensagem = "Usuários não conferem";
        when(usuarioService.update(1L, usuarioDTO)).thenThrow(new IllegalArgumentException(mensagem));

        ResponseEntity<String> response = usuarioController.update(1L, usuarioDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals(mensagem, response.getBody());
    }

    @Test
    void TestControllerDelete() {
        Long id = 1L;
        String mensagem = "Usuário excluido com sucesso!";

        ResponseEntity<String> response = usuarioController.delete(id);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Usuário excluido com sucesso!", response.getBody());
    }
}
