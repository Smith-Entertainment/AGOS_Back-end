package ObrasJava.ObrasJava.ServiceTest;


import AGOS.AGOS.DTO.EnvioDTO;
import AGOS.AGOS.DTO.ObraDTO;
import AGOS.AGOS.config.WebConfig;
import AGOS.AGOS.entity.Envio;
import AGOS.AGOS.repository.EnvioRepository;
import AGOS.AGOS.services.EnvioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = WebConfig.class)
public class EnvioTest {

    @Mock
    private EnvioService envioService;

    private EnvioDTO envioDTO;

    private ObraDTO obraDTO;


    @BeforeEach
    void setup(){


        envioDTO = new EnvioDTO(1L,null,"comentario",null);
        obraDTO = new ObraDTO(1L,"Titulo","Imagem","Objetivo","Licitação",
                null,100,"Bairro","RUA",555,145,
                null,null,541,"Empresa",false,
                null );


        MockitoAnnotations.openMocks(this);



    }



    @Test
    public void findAllTest(){

        List<EnvioDTO> envioDTOList = new ArrayList<>();
        envioDTOList.add(envioDTO);

       when(envioService.findAllEnvios()).thenReturn(envioDTOList);

       List<EnvioDTO> result = envioService.findAllEnvios();

        Assertions.assertEquals(envioDTOList,result);

    }


    @Test
    public void createEnvioTest(){

        when(envioService.createEnvio(envioDTO,obraDTO)).thenReturn(envioDTO);

        EnvioDTO envioCriado = envioService.createEnvio(envioDTO,obraDTO);

        Assertions.assertEquals(envioDTO,envioCriado);

    }

    @Test
    public void updateEnvioTest(){

        when(envioService.updateEnvio(envioDTO,1L)).thenReturn(envioDTO);

        EnvioDTO envioUpdated = envioService.updateEnvio(envioDTO,1L);

        Assertions.assertEquals(envioDTO,envioUpdated);

    }


    @Test
    public void deleteEnvioTest(){

        when(envioService.deleteEnvio(1L)).thenReturn("Envio excluido");

        String result = envioService.deleteEnvio(1L);

        Assertions.assertEquals("Envio excluido",result);




    }




}
