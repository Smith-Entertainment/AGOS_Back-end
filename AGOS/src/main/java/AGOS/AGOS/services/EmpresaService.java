package AGOS.AGOS.services;

import AGOS.AGOS.entity.Empresa;
import AGOS.AGOS.repository.EmpresaRepository;
import AGOS.AGOS.DTO.EmpresaDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa create(EmpresaDTO empresaDTO) {
        assertNotBlank(empresaDTO.getNome(), "O nome da empresa não pode estar em branco");
        assertNotBlank(empresaDTO.getCnpj(), "O CNPJ da empresa não pode estar em branco");

        Empresa empresa = new Empresa();

        BeanUtils.copyProperties(empresaDTO, empresa);

        return empresaRepository.save(empresa);
    }

    private void assertNotBlank(String value, String message) {
        assert !value.isBlank() : message;
    }

    public Empresa update(Long id, EmpresaDTO empresaDTO) {
        Optional<Empresa> empresaExistente = empresaRepository.findById(id);
        if (empresaExistente.isPresent()) {
            Empresa empresa = empresaExistente.get();
            BeanUtils.copyProperties(empresaDTO, empresa);
            empresa.setId(id); // Garanta que o ID seja preservado
            return empresaRepository.save(empresa);
        } else {
            throw new RuntimeException("Empresa não encontrada com o ID: " + id);
        }
    }

    public void delete(Long id) {
        empresaRepository.deleteById(id);
    }

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Empresa getById(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com o ID: " + id));
    }
}
