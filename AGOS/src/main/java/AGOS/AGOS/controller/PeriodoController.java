package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Periodo;
import AGOS.AGOS.repository.PeriodoRepository;
import AGOS.AGOS.services.PeriodoService;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/obra/periodo")
public class PeriodoController {

    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private PeriodoService periodoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable ("id") final  Long id){
        final Periodo periodo = this.periodoRepository.findById(id).orElse(null);

        return periodo == null
                ? ResponseEntity.badRequest().body("Periodo não identificado")
                : ResponseEntity.ok(periodo);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll(){
        final List<Periodo> periodoList = this.periodoRepository.findAll();
        return ResponseEntity.ok(periodoList);
    }

    @GetMapping("/")

    @PostMapping
    public ResponseEntity<?> newPeriodo(@RequestBody final Periodo periodo){
        try{
            this.periodoService.newPeriodo(periodo);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (DataIntegrityViolationException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePeriodo(@PathVariable("id") final Long id, @RequestBody final Periodo periodo){
        try{
            final Periodo verificacao = this.periodoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Não foi possível identificar o registro informado"));
            this.periodoService.updatePeriodo(periodo);
            return ResponseEntity.ok("Registro editado com sucesso");

        } catch (DataIntegrityViolationException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id){
        try{
            final Periodo verificacao = this.periodoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Não foi possível identificar o registro informado"));
            this.periodoService.delete(id);
            return ResponseEntity.ok("Periodo editado com sucesso");
        } catch (DataIntegrityViolationException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}