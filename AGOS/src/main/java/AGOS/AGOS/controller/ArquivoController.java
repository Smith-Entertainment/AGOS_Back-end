package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Arquivo;
import AGOS.AGOS.services.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/obra/arquivo")
public class ArquivoController {

    @Autowired
    ArquivoService arquivoService;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") final Long id) {
        try {
            final Arquivo arquivo = this.arquivoService.findById(id).orElse(null);
            return ResponseEntity.ok(arquivo);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Ops..." + e.getCause());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll(){
        try {
        final List<Arquivo> arquivos = this.arquivoService.findAll();
            return ResponseEntity.ok(arquivos);
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Ops..." + e.getCause());

        }
    }
}
