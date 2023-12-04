package AGOS.AGOS.services;

import AGOS.AGOS.DTO.ObraDTO;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.entity.TipoObra;
import AGOS.AGOS.repository.ObraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Obra.class)
public class ObraServiceTest {

    @InjectMocks
    private ObraService obraService;

    @Mock
    private ObraRepository obraRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testCreateObraInvalid() {
        ObraDTO obraDTO = new ObraDTO();

        assertThrows(IllegalArgumentException.class, () -> obraService.create(obraDTO));

        verify(obraRepository, never()).save(any(Obra.class));
    }

    @Test
    public void testFindById() {
        Long obraId = 1L;
        Obra obra = new Obra();
        when(obraRepository.findById(obraId)).thenReturn(Optional.of(obra));
        when(modelMapper.map(obra, ObraDTO.class)).thenReturn(new ObraDTO());

        ObraDTO obraDTO = obraService.findById(obraId);

    }

    @Test
    public void testFindAll() {
        List<Obra> obras = Collections.singletonList(new Obra());
        when(obraRepository.findAll()).thenReturn(obras);
        when(modelMapper.map(any(Obra.class), eq(ObraDTO.class))).thenReturn(new ObraDTO());

        List<ObraDTO> obraDTOs = obraService.findAll();

    }
}
