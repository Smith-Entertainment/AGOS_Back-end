package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.repository.ObraRepository;
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
        try {
            this.obraRepository.save(obra);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }
    }
}
