package AGOS.AGOS.services;

import AGOS.AGOS.DTO.ObraDTO;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.entity.Situacao;
import AGOS.AGOS.entity.TipoObra;
import AGOS.AGOS.repository.ObraRepository;
import AGOS.AGOS.repository.PeriodoRepository;
import AGOS.AGOS.services.ObraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.mockito.Mockito.*;

class ObraServiceTest {

    @Mock
    private ObraRepository obraRepository;

    @Mock
    private PeriodoRepository periodoRepository;

    @InjectMocks
    private ObraService obraService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateObra() {
        ObraDTO obraDTO = createSampleObraDTO();

        when(obraRepository.save(any())).thenReturn(new Obra());

        obraService.createObra(obraDTO);

        verify(obraRepository, times(1)).save(any(Obra.class));
    }

    @Test
    void testUpdateObra() {
        ObraDTO obraDTO = createSampleObraDTO();
        Long obraId = 1L;

        when(obraRepository.findById(obraId)).thenReturn(java.util.Optional.of(new Obra()));
        when(obraRepository.save(any(Obra.class))).thenReturn(new Obra());

        obraService.updateObra(obraId, obraDTO);

        verify(obraRepository, times(1)).save(any(Obra.class));
    }

    private ObraDTO createSampleObraDTO() {
        ObraDTO obraDTO = new ObraDTO();
        obraDTO.setTitulo("Obra de Teste");
        obraDTO.setCep("12345-678");
        obraDTO.setLicitacao("Licitacao Teste");
        obraDTO.setDataCertame(LocalDate.now());
        obraDTO.setValorEdital(1000.0);
        obraDTO.setBairro("Bairro Teste");
        obraDTO.setRua("Rua Teste");
        obraDTO.setNumero(123);
        obraDTO.setValorContratado(2000.0);
        obraDTO.setDataInicio(LocalDate.now());
        obraDTO.setDataTermino(LocalDate.now().plusDays(30));
        obraDTO.setNumeroContrato(456);
        obraDTO.setEmpresaContratada("Empresa Teste");
        obraDTO.setSituacao(Situacao.EM_ANDAMENTO);
        obraDTO.setTipoObra(TipoObra.INFRAESTRUTURA);

        return obraDTO;
    }
}
