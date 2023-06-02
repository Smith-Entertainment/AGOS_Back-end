package AGOS.AGOS.controller;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.entity.Item;
import AGOS.AGOS.repository.CronogramaRepository;
import AGOS.AGOS.repository.ItemRepository;
import AGOS.AGOS.services.CronogramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/obra/cronograma")
public class CronogramaController {

    @Autowired
    private CronogramaService cronogramaService;
    @Autowired
    private CronogramaRepository cronogramaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Cronograma cronograma = this.cronogramaRepository.findById(id).orElse(null);

        return cronograma == null
                ? ResponseEntity.badRequest().body("Nenhum cronograma encontrado")
                : ResponseEntity.ok(cronograma);
    }
    @GetMapping("/buscarPorItem/{nomeItem}")
    public ResponseEntity<?> buscarPorItem(@PathVariable("nomeItem") final String nomeItem) {
        final List<Cronograma> cronogramas = this.cronogramaRepository.findByItemNome(nomeItem);

        return cronogramas.isEmpty()
                ? ResponseEntity.badRequest().body("Nenhum cronograma encontrado para o item: " + nomeItem)
                : ResponseEntity.ok(cronogramas);
    }



    @GetMapping("/lista")
    public ResponseEntity<?> findAll(){
        final List<Cronograma> cronograma = cronogramaRepository.findAll();

        return ResponseEntity.ok(cronograma);
    }

    @PostMapping
    public ResponseEntity<?> newItem(@RequestBody final Cronograma cronograma){
        try {
            this.cronogramaService.newCronograma(cronograma);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }



}