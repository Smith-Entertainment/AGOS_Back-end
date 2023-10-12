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
    void TestServiceFindById01(){   //Certo
        Usuario usuario = new Usuario(1L, "Andre", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null);
        UsuarioDTO usuarioDTO = new UsuarioDTO(1L, "Andre", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null);
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Mockito.when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);

        UsuarioDTO result = usuarioService.findById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Andre", result.getNome());
    }

    @Test
    void TestServiceFindById02(){   //Falha
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.findById(1L));
    }

    @Test
    void TestServiceCreate01(){   //Certo
        Usuario usuario = new Usuario(1L, "Andre", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null);
        UsuarioDTO usuarioDTO = new UsuarioDTO(1L, "Andre", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null);
        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Mockito.when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);
        Mockito.when(modelMapper.map(usuarioDTO, Usuario.class)).thenReturn(usuario);

        UsuarioDTO result = usuarioService.create(usuarioDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Andre", result.getNome());
    }

    @Test
    void TestServiceCreate02(){   //Falha
        Usuario usuario = new Usuario(1L, "", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null);
        UsuarioDTO usuarioDTO = new UsuarioDTO(1L, "", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null);
        Mockito.when(usuarioRepository.save(usuario)).thenThrow(new IllegalArgumentException("Nome inválido!"));
        Mockito.when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);
        Mockito.when(modelMapper.map(usuarioDTO, Usuario.class)).thenReturn(usuario);

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.create(usuarioDTO));
    }

    @Test
    void TestServiceUpdate01(){   //Certo
        Usuario usuario = new Usuario(1L, "Andre", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null);
        UsuarioDTO usuarioDTO = new UsuarioDTO(1L, "Andre", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null);
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Mockito.when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);
        Mockito.when(modelMapper.map(usuarioDTO, Usuario.class)).thenReturn(usuario);

        UsuarioDTO result = usuarioService.update(1L, usuarioDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Andre", result.getNome());
    }

    @Test
    void TestServiceUpdate02(){   //Falha
        Usuario usuario = new Usuario(1L, "Andre", "123.4563789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null);
        UsuarioDTO usuarioDTO = new UsuarioDTO(1L, "Andre", "123.4563789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null);
        Mockito.when(usuarioRepository.save(usuario)).thenThrow(new IllegalArgumentException("Formato do CPF inválido!"));
        Mockito.when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);
        Mockito.when(modelMapper.map(usuarioDTO, Usuario.class)).thenReturn(usuario);

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.create(usuarioDTO));
    }

    @Test
    void TestServiceDelete01(){   //Certo
        Usuario usuario = new Usuario(1L, "Andre", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null);
        UsuarioDTO usuarioDTO = new UsuarioDTO(1L, "Andre", "123.456.789-10", "andre@email.com", "(45)99973-6277", "123456789012", "andre123",
                null, "Mãe1", LocalDate.of(2002, 4, 12), null);
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Mockito.when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);

        Assertions.assertDoesNotThrow(() -> usuarioService.delete(1L));
    }

    @Test
    void TestServiceDelete02(){   //Falha
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.delete(1L));
    }

}
