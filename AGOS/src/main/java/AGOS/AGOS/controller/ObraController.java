package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.repository.ObraRepository;
import AGOS.AGOS.services.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping(value = "/api/obra")
public class ObraController {
    @Autowired
    ObraService obraService;
    @Autowired
    private ObraRepository obraRepository;
    private Obra obra;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Obra obra = this.obraRepository.findById(id).orElse(null);
        return obra == null
                ? ResponseEntity.badRequest().body("Id não existe ou não foi encontrado")
                : ResponseEntity.ok(obra);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll() {
        final List<Obra> obras = this.obraRepository.findAll();
        return ResponseEntity.ok(obras);

    }

    @PostMapping("/post")
    public ResponseEntity<?> cadastrar(@RequestBody final Obra obra) {
        Long ObraId = obra.getId();
        try {
            if (ObraId != null && obraRepository.existsById(ObraId)) {
                return ResponseEntity.badRequest().body("A obra selecionada já foi cadastrada.");}
            obraService.validaCadastro(obra);
            this.obraRepository.save(obra);
            return ResponseEntity.ok("Obra cadastrada com sucesso!");

        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final Obra obra) {
        try {
            obraService.validaCadastro(obra);
            final Obra obraBanco = this.obraRepository.findById(id).orElse(null);

            if (obraBanco == null || !obraBanco.getId().equals(obra.getId())) {
                return ResponseEntity.badRequest().body("Não foi possível identificar a obra informada");
            }

            this.obraRepository.save(obra);
            return ResponseEntity.ok("Obra editada com sucesso!");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error " + e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        final Obra obra = this.obraRepository.findById(id).orElse(null);
        if (obra == null) {
            return ResponseEntity.badRequest().body("Obra informada não existe ou ja foi deletada");
        } else {
            this.obraRepository.delete(obra);
            return ResponseEntity.ok("Obra deletada com sucesso!");
        }
    }
}



