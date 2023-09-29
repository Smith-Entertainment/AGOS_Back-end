package ObrasJava.ObrasJava.controller;

import AGOS.AGOS.DTO.BairroDTO;
import AGOS.AGOS.controller.BairroController;
import AGOS.AGOS.entity.Bairro;
import AGOS.AGOS.repository.BairroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = Bairro.class)
class BairroControllerTest {
    @Autowired
    BairroController bairroController;
    @MockBean
    BairroRepository bairroRepository;

    @BeforeEach
    void injectData(){
        Bairro bairro1 = new Bairro(1L, "Centro");
        Bairro bairro2 = new Bairro(2L, "Vila A");
        Bairro bairro3 = new Bairro(3L, "Vila Yolanda");
        List<Bairro> bairros = List.of(bairro1, bairro2, bairro3);

        Mockito.when(bairroRepository.findById(1L)).thenReturn(Optional.of(bairro1));
        Mockito.when(bairroRepository.findAll()).thenReturn(bairros);
        Mockito.when(bairroRepository.save(bairro1)).thenReturn(bairro1);
    }

    @Test
    void TestControllerFindById01(){    //Certo
        var resposta = bairroController.findById(1L);
        Long id = resposta.getBody().getId();
        String nome = resposta.getBody().getNome();
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(1L, id);
        Assertions.assertEquals("Centro", nome);
    }

    @Test
    void TestControllerFindById02(){    //Falha
        var resposta = bairroController.findById(2L);
        Long id = resposta.getBody().getId();
        String nome = resposta.getBody().getNome();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        Assertions.assertNull(id);
        Assertions.assertNull(nome);
    }

    @Test
    void TestControllerFindByAll(){
        var resposta = bairroController.findAll();
        int quantidade = resposta.getBody().size();

        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(3, quantidade);
    }

    @Test
    void TestControllerCreate01(){  //Certo
        BairroDTO bairroCriado = new BairroDTO(1L, "Centro");
        String mensagem = "Bairro cadastrado com sucesso!";

        var resposta = bairroController.create(bairroCriado);

        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(mensagem, resposta.getBody());
    }

    @Test
    void TestControllerCreate02(){  //Falha
        BairroDTO bairroCriado = new BairroDTO(1L, "");
        String mensagem = "Nome de bairro inválido!";

        var resposta = bairroController.create(bairroCriado);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        Assertions.assertEquals(mensagem, resposta.getBody());
    }

    @Test
    void TestControllerUpdate01(){  //Certo
        BairroDTO bairroEditado = new BairroDTO(1L, "Central");
        String mensagem = "Bairro editado com sucesso!";

        var resposta = bairroController.update(1L, bairroEditado);

        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(mensagem, resposta.getBody());
    }

    @Test
    void TestControllerUpdate02(){  //Falha
        BairroDTO bairroEditado = new BairroDTO(1L, "    ");
        String mensagem = "Nome de bairro inválido!";

        var resposta = bairroController.update(1L, bairroEditado);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        Assertions.assertEquals(mensagem, resposta.getBody());
    }

    @Test
    void TestControllerDelete01(){  //Certo
        String mensagem = "Bairro excluido com sucesso!";

        var resposta = bairroController.delete(1L);

        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(mensagem, resposta.getBody());
    }

    @Test
    void TestControllerDelete02(){  //Falha
        String mensagem = "Bairro não encontrado!";

        var resposta = bairroController.delete(2L);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        Assertions.assertEquals(mensagem, resposta.getBody());
    }
}
