package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado.")
                : ResponseEntity.ok(obra);
    }
}
