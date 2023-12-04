package AGOS.AGOS.controller;


import AGOS.AGOS.DTO.BairroDTO;
import AGOS.AGOS.services.BairroService;
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

public class BairroControllerTest {
    @InjectMocks
    private BairroController bairroController;

    @Mock
    private BairroService bairroService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void TestControllerFindById01() {   //Certo
        Long id = 1L;
        BairroDTO bairroDTO = new BairroDTO();
        bairroDTO.setId(id);
        when(bairroService.findById(id)).thenReturn(bairroDTO);

        ResponseEntity<BairroDTO> response = bairroController.findById(id);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(bairroDTO, response.getBody());
    }

    @Test
    void TestControllerFindById02() {   //Falha
        Long id = 1L;
        when(bairroService.findById(id)).thenThrow(new IllegalArgumentException("Bairro não encontrado!"));

        ResponseEntity<BairroDTO> response = bairroController.findById(id);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNull(response.getBody().getId());
        Assertions.assertNull(response.getBody().getNome());
    }

    @Test
    void TestControllerFindAll(){
        List<BairroDTO> lista = List.of(new BairroDTO(), new BairroDTO(), new BairroDTO());
        when(bairroService.findAll()).thenReturn(lista);

        ResponseEntity<List<BairroDTO>> response = bairroController.findAll();
        int quantidade = response.getBody().size();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(3, quantidade);
    }



    @Test
    void TestControllerUpdate01() {  //Certo
        Long id = 1L;
        BairroDTO bairroDTO = new BairroDTO();
        bairroDTO.setId(id);
        String mensagem = "Bairro editado com sucesso!";
        when(bairroService.update(id, bairroDTO)).thenReturn(bairroDTO);

        ResponseEntity<String> response = bairroController.update(id, bairroDTO);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(mensagem, response.getBody());
    }

    @Test
    void TestControllerUpdate02() {  //Falha
        Long id = 2L;
        BairroDTO usuarioDTO = new BairroDTO();
        usuarioDTO.setId(id);
        String mensagem = "Bairros não conferem";
        when(bairroService.update(1L, usuarioDTO)).thenThrow(new IllegalArgumentException(mensagem));

        ResponseEntity<String> response = bairroController.update(1L, usuarioDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals(mensagem, response.getBody());
    }

    @Test
    void TestControllerDelete() {
        Long id = 1L;
        String mensagem = "Bairro excluido com sucesso!";

        ResponseEntity<String> response = bairroController.delete(id);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(mensagem, response.getBody());
    }
}
