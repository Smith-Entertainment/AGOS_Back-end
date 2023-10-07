package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.DocumentoDTO;
import AGOS.AGOS.services.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/obra/documento")//MUDAR AQUI PARA NAO SOBRECARREGAR?
public class DocumentoController {
    @Autowired
    DocumentoService documentoService;

    @GetMapping
    public ResponseEntity<DocumentoDTO> findById(@RequestParam("id") final Long id){
        try{
            final DocumentoDTO documento = this.documentoService.findById(id);
            return ResponseEntity.ok(documento);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new DocumentoDTO());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<DocumentoDTO>> findAll(){
        try{
            final List<DocumentoDTO> documentos = this.documentoService.findAll();
            return ResponseEntity.ok(documentos);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ArrayList<>());

        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody final DocumentoDTO documento){
        try{
            this.documentoService.create(documento);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");

        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestParam("id") Long id, @RequestBody DocumentoDTO documento){
        try{
            this.documentoService.update(documento);
            return ResponseEntity.ok("Registro editado com sucesso");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") final Long id){
        try{
            this.documentoService.delete(id);
            return ResponseEntity.ok("Registro removido!");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
