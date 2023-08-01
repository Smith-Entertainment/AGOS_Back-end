package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Periodo;
import AGOS.AGOS.services.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/periodo")
public class PeriodoController {
    @Autowired
    private PeriodoService periodoService;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id")Long id){
        try {
            final Periodo periodo = this.periodoService.findById(id);
            return ResponseEntity.ok(periodo);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
//    @GetMapping
//    public ResponseEntity<?>findAllOf(@RequestParam("id")Long id){
//        try {
//            final List<Periodo> periodo = this.periodoService.findAll(id);
//            return ResponseEntity.ok(periodo);
//        }catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
//        }
//    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody final Periodo periodos){
        try {
            this.periodoService.create(periodos);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestParam("id") Long id,@RequestBody Periodo periodo){
        try {
            this.periodoService.update(periodo);
            return ResponseEntity.ok("Registro Atualizado com Sucesso");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("{\"error\":\""+ e.getMessage() + "\"");
        }
    }

}