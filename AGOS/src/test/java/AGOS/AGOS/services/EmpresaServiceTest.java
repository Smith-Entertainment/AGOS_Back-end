package AGOS.AGOS.services;

import AGOS.AGOS.entity.Empresa;
import AGOS.AGOS.repository.EmpresaRepository;
import AGOS.AGOS.DTO.EmpresaDTO;
import AGOS.AGOS.services.EmpresaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaService empresaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmpresa() {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setNome("Nome da Empresa");
        empresaDTO.setCnpj("12345678901234");

        Empresa empresa = new Empresa();
        empresa.setNome(empresaDTO.getNome());
        empresa.setCnpj(empresaDTO.getCnpj());

        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);

        Empresa result = empresaService.create(empresaDTO);

        assertNotNull(result);
        assertEquals(empresaDTO.getNome(), result.getNome());
        assertEquals(empresaDTO.getCnpj(), result.getCnpj());

        verify(empresaRepository, times(1)).save(any(Empresa.class));
    }

    @Test
    public void testUpdateEmpresa() {
        Long id = 1L;
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setNome("Nome Atualizado");
        empresaDTO.setCnpj("98765432109876");

        Empresa empresaExistente = new Empresa();
        empresaExistente.setId(id);
        empresaExistente.setNome("Nome Antigo");
        empresaExistente.setCnpj("12345678901234");

        when(empresaRepository.findById(id)).thenReturn(Optional.of(empresaExistente));
        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresaExistente);

        Empresa result = empresaService.update(id, empresaDTO);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(empresaDTO.getNome(), result.getNome());
        assertEquals(empresaDTO.getCnpj(), result.getCnpj());

        verify(empresaRepository, times(1)).findById(id);
        verify(empresaRepository, times(1)).save(any(Empresa.class));
    }

    @Test
    public void testDeleteEmpresa() {
        Long id = 1L;

        empresaService.delete(id);

        verify(empresaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindAllEmpresas() {
        List<Empresa> empresas = Arrays.asList(new Empresa(), new Empresa());

        when(empresaRepository.findAll()).thenReturn(empresas);

        List<Empresa> result = empresaService.findAll();

        assertNotNull(result);
        assertEquals(empresas.size(), result.size());

        verify(empresaRepository, times(1)).findAll();
    }

    @Test
    public void testGetEmpresaById() {
        Long id = 1L;
        Empresa empresa = new Empresa();
        empresa.setId(id);

        when(empresaRepository.findById(id)).thenReturn(Optional.of(empresa));

        Empresa result = empresaService.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());

        verify(empresaRepository, times(1)).findById(id);
    }
}
