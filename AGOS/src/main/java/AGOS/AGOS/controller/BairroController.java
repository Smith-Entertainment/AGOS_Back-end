package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Bairro;
import AGOS.AGOS.services.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/bairro")
public class BairroController {
    @Autowired
    private BairroService bairroService;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") final Long id){
        try {
            Bairro bairro = this.bairroService.findById(id);
            return ResponseEntity.ok(bairro);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll(){
        try {
            List<Bairro> bairros = this.bairroService.findAll();
            return ResponseEntity.ok(bairros);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final Bairro bairro){
        try {
            this.bairroService.create(bairro);
            return ResponseEntity.ok("Bairro cadastrado com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam("id") final Long id, @RequestBody final Bairro bairro){
        try {
            this.bairroService.update(id, bairro);
            return ResponseEntity.ok("Bairro editado com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") final Long id){
        try {
            this.bairroService.delete(id);
            return ResponseEntity.ok("Bairro excluido com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}