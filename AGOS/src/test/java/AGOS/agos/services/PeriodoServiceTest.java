package AGOS.agos.services;

import AGOS.agos.dto.PeriodoDTO;
import AGOS.agos.entity.Periodo;
import AGOS.agos.repository.PeriodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Periodo.class)
class PeriodoServiceTest {
    @InjectMocks
    PeriodoService periodoService;
    @Mock
    PeriodoRepository periodoRepository;
    @Mock
    ModelMapper modelMapper;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeEach
    void injectData() {
        Periodo periodo = new Periodo();
        periodo.setId(1L);
        when(periodoRepository.findById(1L)).thenReturn(Optional.of(periodo));
    }
    @Test
    void TestServiceFindById01() {
        Long id = 1L;
        Periodo periodo = new Periodo();
        periodo.setId(id);

        PeriodoDTO periodoDTO = new PeriodoDTO();
        periodoDTO.setId(id);

        when(periodoRepository.findById(id)).thenReturn(Optional.of(periodo));
        when(modelMapper.map(periodo, PeriodoDTO.class)).thenReturn(periodoDTO);

        PeriodoDTO result = periodoService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void TestServiceFindById02() {
        Long id = 1L;
        when(periodoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> periodoService.findById(id));
    }

    @Test
    void TestServiceCreate01() {
        PeriodoDTO periodoDTO = createValidPeriodoDTO();
        Periodo periodo = periodoService.toPeriodo(periodoDTO);
        when(modelMapper.map(periodo, PeriodoDTO.class)).thenReturn(periodoDTO);

        when(periodoRepository.save(any(Periodo.class))).thenReturn(periodo);

        PeriodoDTO result = periodoService.create(periodoDTO);

        assertNotNull(result);
    }

    @Test
    void TestServiceCreate02() {
        PeriodoDTO periodoDTO = createValidPeriodoDTO();
        periodoDTO.setAno(100); // Ano inválido

        assertThrows(IllegalArgumentException.class, () -> periodoService.create(periodoDTO));
    }

    @Test
    void TestServiceUpdate01() {
        Long id = 1L;
        PeriodoDTO periodoDTO = createValidPeriodoDTO();
        periodoDTO.setId(id);

        Periodo periodo = periodoService.toPeriodo(periodoDTO);


        when(periodoRepository.save(any(Periodo.class))).thenReturn(new Periodo());
        when(modelMapper.map(periodo, PeriodoDTO.class)).thenReturn(periodoDTO);
        PeriodoDTO result = periodoService.update(periodoDTO);

        Assertions.assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void TestServiceUpdate02() {
        Long id = 1L;
        PeriodoDTO periodoDTO = createValidPeriodoDTO();
        periodoDTO.setId(id);
        periodoDTO.setAno(100); // Ano inválido

        assertThrows(IllegalArgumentException.class, () -> periodoService.update(periodoDTO));
    }

    @Test
    void TestServiceDelete01() {
        Long id = 1L;
        Periodo periodo = new Periodo();
        periodo.setId(id);
        when(periodoRepository.findById(id)).thenReturn(Optional.of(periodo));

        assertDoesNotThrow(() -> periodoService.delete(id));
    }

    @Test
    void TestServiceDelete02() {
        Long id = 1L;
        when(periodoRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> periodoService.delete(id));
    }


    private PeriodoDTO createValidPeriodoDTO() {
        PeriodoDTO periodoDTO = new PeriodoDTO();
        periodoDTO.setAno(2023);
        return periodoDTO;
    }
}