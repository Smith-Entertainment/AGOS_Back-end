package ObrasJava.ObrasJava;

import AGOS.AGOS.controller.EmpresaController;
import AGOS.AGOS.DTO.EmpresaDTO;
import AGOS.AGOS.entity.Empresa;
import AGOS.AGOS.services.EmpresaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmpresaControllerTest {

    @InjectMocks
    private EmpresaController controller;

    @Mock
    private EmpresaService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        Long empresaId = 1L;
        Empresa empresa = new Empresa();

        when(service.getById(empresaId)).thenReturn(empresa);

        ResponseEntity<?> response = controller.findById(empresaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresa, response.getBody());

        verify(service, times(1)).getById(empresaId);
    }

    @Test
    void testFindAll() {
        List<Empresa> empresas = new ArrayList<>();
        empresas.add(new Empresa());
        empresas.add(new Empresa());

        when(service.findAll()).thenReturn(empresas);

        ResponseEntity<?> response = controller.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresas, response.getBody());

        verify(service, times(1)).findAll();
    }

    @Test
    void testCreate() {
        EmpresaDTO empresaDTO = new EmpresaDTO();

        ResponseEntity<?> response = controller.create(empresaDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registro cadastrado com sucesso", response.getBody());

        verify(service, times(1)).create(empresaDTO);
    }

    @Test
    void testCreateWithException() {
        EmpresaDTO empresaDTO = new EmpresaDTO();

        when(service.create(empresaDTO)).thenThrow(new RuntimeException("Erro"));

        ResponseEntity<?> response = controller.create(empresaDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro", response.getBody());

        verify(service, times(1)).create(empresaDTO);
    }

    @Test
    void testUpdate() {
        Long empresaId = 1L;
        EmpresaDTO empresaDTO = new EmpresaDTO();

        ResponseEntity<?> response = controller.update(empresaId, empresaDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registro editado com sucesso", response.getBody());

        verify(service, times(1)).update(empresaId, empresaDTO);
    }

    @Test
    void testUpdateWithException() {
        Long empresaId = 1L;
        EmpresaDTO empresaDTO = new EmpresaDTO();

        when(service.update(empresaId, empresaDTO)).thenThrow(new RuntimeException("Erro"));

        ResponseEntity<?> response = controller.update(empresaId, empresaDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro", response.getBody());

        verify(service, times(1)).update(empresaId, empresaDTO);
    }

    @Test
    void testDelete() {
        Long empresaId = 1L;

        ResponseEntity<?> response = controller.delete(empresaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registro excluído com sucesso", response.getBody());

        verify(service, times(1)).delete(empresaId);
    }

}
