package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Valor;
import AGOS.AGOS.services.ValorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/valor")
public class ValorController {
    @Autowired
    private ValorService cronogramaService;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id")final Long id){
        try {
            final Valor valor = this.cronogramaService.findById(id);
            return ResponseEntity.ok(valor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?>findAll(){
        try{
            final List<Valor> valorList = this.cronogramaService.findAll();
            return ResponseEntity.ok(valorList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping
    public ResponseEntity<?>create(@RequestBody final Valor valor){
        try {
            this.cronogramaService.create(valor);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestParam("id") final Long id, @RequestBody final Valor valor) {
        try {
            this.cronogramaService.update(valor);
            return ResponseEntity.ok("Registro editado com sucesso");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

}
