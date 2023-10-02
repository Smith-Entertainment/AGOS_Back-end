package ObrasJava.ObrasJava.DTOTest;


import AGOS.AGOS.config.WebConfig;
import AGOS.AGOS.entity.Envio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(classes = WebConfig.class)
public class EnvioTest {






    @Test
    public void envioGetterTest(){
        Envio envio = new Envio(1L, LocalDate.now(),null,"Endereco arquivo","Comentario",null);


        Assertions.assertEquals(1L,envio.getId());
        Assertions.assertEquals(LocalDate.now(),envio.getData());
        Assertions.assertNull(envio.getVoluntario());
        Assertions.assertEquals("Comentario",envio.getComentario());
        Assertions.assertNull(envio.getObra());

    }


}
