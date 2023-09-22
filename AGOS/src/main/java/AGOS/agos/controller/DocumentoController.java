package AGOS.agos.controller;

import AGOS.agos.entity.Documento;
import AGOS.agos.services.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/obra/documento")
public class DocumentoController {
    @Autowired
    DocumentoService documentoService;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") final Long id){
        try{
            final Documento documento = this.documentoService.findById(id).orElse(null);
            return ResponseEntity.ok(documento);
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Ops..." + e.getCause());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll(){
        try{
            final List<Documento> documentos = this.documentoService.findAll();
            return ResponseEntity.ok(documentos);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Ops..." + e.getCause());

        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final Documento documento){
        try{
            this.documentoService.create(documento);
            return ResponseEntity.ok(documento);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Ops..." + e.getCause());

        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam("id") Long id, @RequestBody final Documento documento){
        try{
            this.documentoService.update(documento, id);
            return ResponseEntity.ok(documento);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Ops..." + e.getCause());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") final Long id){
        try{
            this.documentoService.delete(id);
            return ResponseEntity.ok("Arquivo removido!");
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Ops..." + e.getCause());
        }
    }
}
