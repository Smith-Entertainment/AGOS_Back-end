package ObrasJava.ObrasJava.controller;

import AGOS.AGOS.DTO.UsuarioDTO;
import AGOS.AGOS.controller.UsuarioController;
import AGOS.AGOS.entity.Usuario;
import AGOS.AGOS.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = Usuario.class)
class UsuarioControllerTest {
    @Autowired
    UsuarioController usuarioController;
    @MockBean
    UsuarioRepository usuarioRepository;

    @BeforeEach
    void injectData(){
        Usuario usuario1 = new Usuario(1L, "Andre", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null, null);
        Usuario usuario2 = new Usuario(2L, "Cleyton", "109.876.543-21", "cleyton@email.com", "(45)99973-9999", "123456987012", "cleyton123",
                null, "Mãe2", LocalDate.of(2000, 4, 8), null, null);
        Usuario usuario3 = new Usuario(3L, "Luis", "123.645.879-10", "luis@email.com", "(45)99999-6277", "123456789012", "luis123",
                null, "Mãe3", LocalDate.of(1999, 12, 26), null, null);
        List<Usuario> usuarios = List.of(usuario1, usuario2, usuario3);

        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario1));
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);
        Mockito.when(usuarioRepository.save(usuario1)).thenReturn(usuario1);
    }

    @Test
    void TestControllerFindById01(){    //Certo
        var resposta = usuarioController.findById(1L);
        Long id = resposta.getBody().getId();
        String nome = resposta.getBody().getNome();
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(1L, id);
        Assertions.assertEquals("Andre", nome);
    }

    @Test
    void TestControllerFindById02(){    //Falha
        var resposta = usuarioController.findById(2L);
        Long id = resposta.getBody().getId();
        String nome = resposta.getBody().getNome();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        Assertions.assertNull(id);
        Assertions.assertNull(nome);
    }

    @Test
    void TestControllerFindByAll(){
        var resposta = usuarioController.findAll();
        int quantidade = resposta.getBody().size();

        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(3, quantidade);
    }

    @Test
    void TestControllerCreate01(){  //Certo
        UsuarioDTO bairroCriado = new UsuarioDTO(1L, "Andre", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null, null);
        String mensagem = "Usuário cadastrado com sucesso!";

        var resposta = usuarioController.create(bairroCriado);

        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(mensagem, resposta.getBody());
    }

    @Test
    void TestControllerCreate02(){  //Falha
        UsuarioDTO bairroCriado = new UsuarioDTO(1L, "", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null, null);
        String mensagem = "Nome inválido!";

        var resposta = usuarioController.create(bairroCriado);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        Assertions.assertEquals(mensagem, resposta.getBody());
    }

    @Test
    void TestControllerUpdate01(){  //Certo
        UsuarioDTO bairroEditado = new UsuarioDTO(1L, "Andre", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Nelci", LocalDate.of(2002, 4, 12), null, null);
        String mensagem = "Usuário editado com sucesso!";

        var resposta = usuarioController.update(1L, bairroEditado);

        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(mensagem, resposta.getBody());
    }

    @Test
    void TestControllerUpdate02(){  //Falha
        UsuarioDTO bairroEditado = new UsuarioDTO(1L, "Andre", "123.456789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null, null);
        String mensagem = "CPF inválido!";

        var resposta = usuarioController.update(1L, bairroEditado);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        Assertions.assertEquals(mensagem, resposta.getBody());
    }

    @Test
    void TestControllerDelete01(){  //Certo
        String mensagem = "Usuário excluido com sucesso!";

        var resposta = usuarioController.delete(1L);

        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(mensagem, resposta.getBody());
    }

    @Test
    void TestControllerDelete02(){  //Falha
        String mensagem = "Usuário não encontrado!";

        var resposta = usuarioController.delete(2L);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        Assertions.assertEquals(mensagem, resposta.getBody());
    }
}
