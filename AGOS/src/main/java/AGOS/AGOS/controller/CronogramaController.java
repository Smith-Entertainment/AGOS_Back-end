package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.entity.Periodo;
import AGOS.AGOS.repository.CronogramaRepository;
import AGOS.AGOS.services.CronogramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;


@Controller
@RequestMapping("/api/cronograma")
public class CronogramaController {

    @Autowired
    private CronogramaService cronogramaService;

    @GetMapping
    public ResponseEntity<?>findById(@RequestParam("id")final Long id){
        final Cronograma cronograma = this.cronogramaService.findById(id);
        return ResponseEntity.ok(cronograma);
    }

    @GetMapping("/List")
    public ResponseEntity<?>findAll(){
        final List<Cronograma> cronogramaList = this.cronogramaService.findAll();
        return ResponseEntity.ok(cronogramaList);
    }

    @PostMapping
    public ResponseEntity<?>create(@RequestBody final Cronograma cronograma){
        try {
            this.cronogramaService.create(cronograma);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestParam("id") final Long id, @RequestBody final Cronograma cronograma) {
        try {
            this.cronogramaService.update(cronograma);
            return ResponseEntity.ok("Registro editado com sucesso");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }



}