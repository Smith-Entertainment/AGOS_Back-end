package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.EmpresaDTO;
import AGOS.AGOS.entity.Empresa;
import AGOS.AGOS.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Empresa empresa = empresaService.getById(id);
        return ResponseEntity.ok(empresa);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll() {
        List<Empresa> empresas = empresaService.findAll();
        return ResponseEntity.ok(empresas);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmpresaDTO empresaDTO) {
        try {
            Empresa empresa = empresaService.create(empresaDTO);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO) {
        try {
            Empresa empresa = empresaService.update(id, empresaDTO);
            return ResponseEntity.ok("Registro editado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            empresaService.delete(id);
            return ResponseEntity.ok("Registro excluído com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}