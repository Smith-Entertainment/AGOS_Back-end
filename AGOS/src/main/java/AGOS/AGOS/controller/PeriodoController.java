package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.entity.Periodo;
import AGOS.AGOS.repository.PeriodoRepository;
import AGOS.AGOS.services.PeriodoService;
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
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Periodo periodo = this.periodoRepository.findById(id).orElse(null);

        return periodo == null
                ? ResponseEntity.badRequest().body("Periodo não identificado")
                : ResponseEntity.ok(periodo);
    }
    @GetMapping("/lista-obra:{id}")
    public ResponseEntity<List<Periodo>> getPeriodosByObraId(@PathVariable Long id) {
        List<Periodo> periodos = periodoRepository.findByObraId(id);

        return ResponseEntity.ok(periodos);
    }
    @PostMapping
    public ResponseEntity<?> newItem(@RequestBody final Periodo periodos){
        try {
            this.periodoRepository.save(periodos);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }










}