package AGOS.AGOS.services;

import AGOS.AGOS.DTO.UsuarioDTO;
import AGOS.AGOS.entity.Usuario;
import AGOS.AGOS.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

public class UsuarioServiceTest {
    @InjectMocks
    UsuarioService usuarioService;
    @Mock
    UsuarioRepository usuarioRepository;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void TestServiceFindById02(){   //Falha
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.findById(1L));
    }



    @Test
    void TestServiceDelete02(){   //Falha
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.delete(1L));
    }
}
