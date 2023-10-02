package AGOS.AGOS.services;
import AGOS.AGOS.DTO.ValorDTO;
import AGOS.AGOS.entity.Item;
import AGOS.AGOS.entity.Periodo;
import AGOS.AGOS.entity.Valor;
import AGOS.AGOS.repository.ValorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes =Valor.class)
 class ValorServiceTest {
    @InjectMocks
     ValorService valorService;
    @Mock
     ValorRepository valorRepository;
     @Mock
     ModelMapper modelMapper;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
     @BeforeEach
     void injectData() {
         Valor valor = new Valor();
         valor.setId(1L);
         when(valorRepository.findById(1L)).thenReturn(Optional.of(valor));
     }
     @Test
     void TestServiceFindById01() {
         Long id = 1L;
         Valor valor = new Valor();
         valor.setId(id);
         ValorDTO valorDTO = new ValorDTO();
         valorDTO.setId(id);
         when(valorRepository.findById(id)).thenReturn(Optional.of(valor));
         when(modelMapper.map(valor, ValorDTO.class)).thenReturn(valorDTO);
         ValorDTO result = valorService.findById(id);
         assertNotNull(result);
         assertEquals(id, result.getId());
     }
@Test
     void TestServiceFindById02() {
        Long id = 1L;
        when(valorRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> valorService.findById(id));
    }

     @Test
     void TestServiceCreate01() {
         ValorDTO valorDTO = createValidValorDTO();
         Valor valor = valorService.toValor(valorDTO);
         when(modelMapper.map(valor, ValorDTO.class)).thenReturn(valorDTO);
         when(valorRepository.save(any(Valor.class))).thenReturn(valor);
         ValorDTO result = valorService.create(valorDTO);
         assertNotNull(result);
     }


     @Test
     void TestServiceCreate02() {
        ValorDTO valorDTO = new ValorDTO();
        assertThrows(IllegalArgumentException.class, () -> valorService.create(valorDTO));
    }
     @Test
     void TestServiceUpdate01() {
         Long id = 1L;
         ValorDTO valorDTO = createValidValorDTO();
         valorDTO.setId(id);
         Valor valor = valorService.toValor(valorDTO);
         when(valorRepository.save(any(Valor.class))).thenReturn(valor);
         when(modelMapper.map(valor, ValorDTO.class)).thenReturn(valorDTO);
         ValorDTO result = valorService.update(valorDTO);
         assertNotNull(result);
         assertEquals(id, result.getId());
     }

    @Test
     void TestServiceUpdate02() {
        ValorDTO valorDTO = new ValorDTO(); // ValorDTO inválido
        assertThrows(IllegalArgumentException.class, () -> valorService.update(valorDTO));
    }
     @Test
     void TestServiceDelete01() {
         Long id = 1L;
         Valor valor = new Valor();
         valor.setId(id);
         when(valorRepository.findById(id)).thenReturn(Optional.of(valor));
         valorService.delete(id);
         verify(valorRepository, times(1)).deleteById(id);
     }
     @Test
     void TestServiceDelete02() {
         ValorDTO valorDTO = new ValorDTO();
         assertThrows(IllegalArgumentException.class, () -> {
             valorService.create(valorDTO);
         });
     }
    private ValorDTO createValidValorDTO() {
        ValorDTO valorDTO = new ValorDTO();
        Item item = new Item();
        Periodo periodo = new Periodo();
        valorDTO.setItem(item);
        valorDTO.setPeriodo(periodo);
        valorDTO.setPrevistoFisico(new BigDecimal("100"));
        valorDTO.setRealizadoFisico(new BigDecimal("90"));
        valorDTO.setPrevistoFinanceiro(new BigDecimal("1000.0"));
        valorDTO.setRealizadoFinanceiro(new BigDecimal("900.0"));
        return valorDTO;
    }
}
