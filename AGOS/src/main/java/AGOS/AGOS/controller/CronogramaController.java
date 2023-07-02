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

    @GetMapping("/List-cronogrma-obra:{id}")
    public ResponseEntity<?> findByCronogramasObraId(@PathVariable Long id) {
        List<Cronograma> cronogramaList = cronogramaRepository.findByPeriodoObraId(id);

        return ResponseEntity.ok(cronogramaList);
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
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final Cronograma cronograma) {
        try {
            final Cronograma cronogramaBanco = this.cronogramaRepository.findById(id).orElse(null);

            if (cronogramaBanco == null || !cronogramaBanco.getId().equals(cronograma.getId())) {
                throw new RuntimeException("Não foi possível identificar o registro informado");
            }

            // Copiar os valores do objeto cronograma para cronogramaBanco
            cronogramaBanco.setPrevistoFinanceiro(cronograma.getPrevistoFinanceiro());
            cronogramaBanco.setRealizadoFinanceiro(cronograma.getRealizadoFinanceiro());
            // Copie outras propriedades, se houver

            this.cronogramaRepository.save(cronogramaBanco);
            return ResponseEntity.ok("Registro editado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error " + e.getMessage());
        }
    }



}