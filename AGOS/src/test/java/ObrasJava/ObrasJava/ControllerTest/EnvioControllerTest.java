package ObrasJava.ObrasJava.ControllerTest;

import AGOS.AGOS.DTO.EnvioDTO;
import AGOS.AGOS.DTO.ObraDTO;
import AGOS.AGOS.config.WebConfig;
import AGOS.AGOS.controller.EnvioController;
import AGOS.AGOS.entity.Envio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WebConfig.class)
public class EnvioControllerTest {

    @Mock
    private EnvioController envioController;


    private Envio envioEntity;

    private EnvioDTO envioDTO;

    private ObraDTO obraDTO;


    @BeforeEach
    public void setup (){

        obraDTO = new ObraDTO(1L,"Titulo","Imagem","Objetivo","Licitação",
                null,100,"Bairro","RUA",555,145,
                null,null,541,"Empresa",false,
                null );

        envioDTO = new EnvioDTO(1L,null,"comentario",obraDTO);


        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void findAllEnviosTest()  {

        List<EnvioDTO> listEnvioDTO = new ArrayList<>();

        listEnvioDTO.add(envioDTO);

        when(envioController.findAllEnvios()).thenReturn(ResponseEntity.ok(listEnvioDTO));

        ResponseEntity<List<EnvioDTO>> result = envioController.findAllEnvios();

        Assertions.assertEquals(ResponseEntity.ok(listEnvioDTO),result);


    }

    @Test
    public void createEnvioTest()  {


        when(envioController.realizarEnvio(envioDTO,obraDTO)).thenReturn(ResponseEntity.ok("Envio cadastrado com sucesso"));

        ResponseEntity<String> result = envioController.realizarEnvio(envioDTO,obraDTO);

        Assertions.assertEquals(ResponseEntity.ok("Envio cadastrado com sucesso"),result);


    }

    @Test
    public void UpdateEnvioTest()  {


        when(envioController.atualizarEnvio(envioDTO,1L)).thenReturn(ResponseEntity.ok("Envio modificado com sucesso"));

        ResponseEntity<String> result = envioController.atualizarEnvio(envioDTO,1L);

        Assertions.assertEquals(ResponseEntity.ok("Envio modificado com sucesso"),result);


    }

    @Test
    public void deletarEnvioTest()  {


        when(envioController.deletarEnvio(1L)).thenReturn(ResponseEntity.ok("Envio Excluido"));

        ResponseEntity<String> result = envioController.deletarEnvio(1L);

        Assertions.assertEquals(ResponseEntity.ok("Envio Excluido"),result);


    }







}
