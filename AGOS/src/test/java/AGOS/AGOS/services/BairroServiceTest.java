package AGOS.AGOS.services;

import AGOS.AGOS.DTO.BairroDTO;
import AGOS.AGOS.entity.Bairro;
import AGOS.AGOS.repository.BairroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class BairroServiceTest {
    @InjectMocks
    BairroService bairroService;
    @Mock
    BairroRepository bairroRepository;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void TestServiceFindById01(){   //Certo
        Bairro bairro = new Bairro(1L, "Centro");
        BairroDTO bairroDTO = new BairroDTO(1L, "Centro");
        Mockito.when(bairroRepository.findById(1L)).thenReturn(Optional.of(bairro));
        Mockito.when(modelMapper.map(bairro, BairroDTO.class)).thenReturn(bairroDTO);

        BairroDTO result = bairroService.findById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Centro", result.getNome());
    }

    @Test
    void TestServiceFindById02(){   //Falha
        Mockito.when(bairroRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> bairroService.findById(1L));
    }

    @Test
    void TestServiceCreate01(){   //Certo
        Bairro bairro = new Bairro(1L, "Centro");
        BairroDTO bairroDTO = new BairroDTO(1L, "Centro");
        Mockito.when(bairroRepository.save(bairro)).thenReturn(bairro);
        Mockito.when(modelMapper.map(bairro, BairroDTO.class)).thenReturn(bairroDTO);
        Mockito.when(modelMapper.map(bairroDTO, Bairro.class)).thenReturn(bairro);

        BairroDTO result = bairroService.create(bairroDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Centro", result.getNome());
    }

    @Test
    void TestServiceCreate02(){   //Falha
        Bairro bairro = new Bairro(1L, "");
        BairroDTO bairroDTO = new BairroDTO(1L, "");
        Mockito.when(bairroRepository.save(bairro)).thenThrow(new IllegalArgumentException("Nome de bairro inválido!"));
        Mockito.when(modelMapper.map(bairro, BairroDTO.class)).thenReturn(bairroDTO);
        Mockito.when(modelMapper.map(bairroDTO, Bairro.class)).thenReturn(bairro);

        Assertions.assertThrows(IllegalArgumentException.class, () -> bairroService.create(bairroDTO));
    }

    @Test
    void TestServiceUpdate01(){   //Certo
        Bairro bairro = new Bairro(1L, "Centro");
        BairroDTO bairroDTO = new BairroDTO(1L, "Centro");
        Mockito.when(bairroRepository.findById(1L)).thenReturn(Optional.of(bairro));
        Mockito.when(bairroRepository.save(bairro)).thenReturn(bairro);
        Mockito.when(modelMapper.map(bairro, BairroDTO.class)).thenReturn(bairroDTO);
        Mockito.when(modelMapper.map(bairroDTO, Bairro.class)).thenReturn(bairro);

        BairroDTO result = bairroService.update(1L, bairroDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Centro", result.getNome());
    }

    @Test
    void TestServiceUpdate02(){   //Falha
        Bairro bairro = new Bairro(1L, "Centro");
        BairroDTO bairroDTO = new BairroDTO(1L, "Centro");
        Mockito.when(bairroRepository.save(bairro)).thenThrow(new IllegalArgumentException("Registros não conferem"));
        Mockito.when(modelMapper.map(bairro, BairroDTO.class)).thenReturn(bairroDTO);
        Mockito.when(modelMapper.map(bairroDTO, Bairro.class)).thenReturn(bairro);

        Assertions.assertThrows(IllegalArgumentException.class, () -> bairroService.create(bairroDTO));
    }

    @Test
    void TestServiceDelete01(){   //Certo
        Bairro bairro = new Bairro(1L, "Centro");
        BairroDTO bairroDTO = new BairroDTO(1L, "Centro");
        Mockito.when(bairroRepository.findById(1L)).thenReturn(Optional.of(bairro));
        Mockito.when(modelMapper.map(bairro, BairroDTO.class)).thenReturn(bairroDTO);

        Assertions.assertDoesNotThrow(() -> bairroService.delete(1L));
    }

    @Test
    void TestServiceDelete02(){   //Falha
        Mockito.when(bairroRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> bairroService.delete(1L));
    }
}
