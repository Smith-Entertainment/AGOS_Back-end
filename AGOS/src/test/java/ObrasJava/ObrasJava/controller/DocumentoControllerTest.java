package ObrasJava.ObrasJava.controller;

import AGOS.AGOS.DTO.DocumentoDTO;
import AGOS.AGOS.controller.DocumentoController;
import AGOS.AGOS.entity.Documento;
import AGOS.AGOS.repository.DocumentoRepository;
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
@SpringBootTest(classes = Documento.class)
public class DocumentoControllerTest{
    @Autowired
    DocumentoController documentoController;
    @MockBean
    DocumentoRepository documentoRepository;

//    @BeforeEach
//    void injectData(){
//        Documento documento1 = new Documento(1L, "Documento1", "Rua A");
//        Documento documento2 = new Documento(2L, "Documento2", "Rua B");
//        List<Documento> documentos = List.of(documento1, documento2);
//
//        Mockito.when(documentoRepository.findById(1L)).thenReturn(Optional.of(documento1));
//        Mockito.when(documentoRepository.findAll()).thenReturn(documentos);
//        Mockito.when(documentoRepository.save(documento1)).thenReturn(documento1);
//    }
//
//    @Test
//    void TestControllerFindById01(){    //Certo
//        var resposta = documentoController.findById(1L);
//        Long id = resposta.getBody().getId();
//        String nome = resposta.getBody().getNome();
//        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
//        Assertions.assertEquals(1L, id);
//        Assertions.assertEquals("Documento1", nome);
//    }
//
//    @Test
//    void TestControllerFindById02(){    //Falha
//        var resposta = documentoController.findById(2L);
//        Long id = resposta.getBody().getId();
//        String nome = resposta.getBody().getNome();
//        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
//        Assertions.assertNull(id);
//        Assertions.assertNull(nome);
//    }
//
//    @Test
//    void TestControllerFindByAll(){
//        var resposta = documentoController.findAll();
//        int quantidade = resposta.getBody().size();
//
//        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
//        Assertions.assertEquals(3, quantidade);
//    }
//
//    @Test
//    void TestControllerCreate01(){  //Certo
//        DocumentoDTO documentoCriado = new DocumentoDTO(1L, "Documento1", "Rua A");
//        String mensagem = "Documento cadastrado com sucesso!";
//
//        var resposta = documentoController.create(documentoCriado);
//
//        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
//        Assertions.assertEquals(mensagem, resposta.getBody());
//    }
//
//    @Test
//    void TestControllerCreate02(){  //Falha
//        DocumentoDTO documentoCriado = new DocumentoDTO(1L, "");
//        String mensagem = "Nome de documento inválido!";
//
//        var resposta = documentoController.create(documentoCriado);
//
//        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
//        Assertions.assertEquals(mensagem, resposta.getBody());
//    }
//
//    @Test
//    void TestControllerUpdate01(){  //Certo
//        DocumentoDTO documentoEditado = new DocumentoDTO(1L, "DocumentoEditado1");
//        String mensagem = "Documento editado com sucesso!";
//
//        var resposta = documentoController.update(1L, documentoEditado);
//
//        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
//        Assertions.assertEquals(mensagem, resposta.getBody());
//    }
//
//    @Test
//    void TestControllerUpdate02(){  //Falha
//        DocumentoDTO documentoEditado = new DocumentoDTO(1L, "    ");
//        String mensagem = "Nome de bairro inválido!";
//
//        var resposta = documentoController.update(1L, documentoEditado);
//
//        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
//        Assertions.assertEquals(mensagem, resposta.getBody());
//    }
//
//    @Test
//    void TestControllerDelete01(){  //Certo
//        String mensagem = "Documento excluido com sucesso!";
//
//        var resposta = documentoController.delete(1L);
//
//        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
//        Assertions.assertEquals(mensagem, resposta.getBody());
//    }
//
//    @Test
//    void TestControllerDelete02(){  //Falha
//        String mensagem = "Documento não encontrado!";
//
//        var resposta = documentoController.delete(2L);
//
//        Assertions.assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
//        Assertions.assertEquals(mensagem, resposta.getBody());
//    }
}
