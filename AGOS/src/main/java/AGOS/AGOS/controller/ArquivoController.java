package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Arquivo;
import AGOS.AGOS.services.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/obra/arquivo")
public class ArquivoController {

    @Autowired
    ArquivoService arquivoService;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") final Long id) {
        try{
            final Arquivo arquivo = this.arquivoService.findById(id).orElse(null);
            return ResponseEntity.ok(arquivo);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Ops..." + e.getCause());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll(){
        try{
            final List<Arquivo> arquivos = this.arquivoService.findAll();
            return ResponseEntity.ok(arquivos);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Ops..." + e.getCause());

        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final Arquivo arquivo){
        try{
            this.arquivoService.create(arquivo);
            return ResponseEntity.ok(arquivo);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Ops..." + e.getCause());

        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam("id") Long id, @RequestBody final Arquivo arquivo){
        try{
            this.arquivoService.update(arquivo, id);
            return ResponseEntity.ok(arquivo);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Ops..." + e.getCause());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") final Long id){
        try{
            this.arquivoService.delete(id);
            return ResponseEntity.ok("Arquivo removido!");
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Ops..." + e.getCause());
        }
    }

}
