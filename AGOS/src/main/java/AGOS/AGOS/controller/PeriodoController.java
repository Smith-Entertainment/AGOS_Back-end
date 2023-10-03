package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.PeriodoDTO;
import AGOS.AGOS.services.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/periodos")
public class PeriodoController {
    @Autowired
    private  PeriodoService periodoService;
    @GetMapping
    public ResponseEntity<PeriodoDTO> findById(@RequestParam("id") final Long id){
        try {
<<<<<<< HEAD
            final Periodo periodo = this.periodoService.findById(id);
            return ResponseEntity.ok(periodo);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

=======
            PeriodoDTO periodoDTO = this.periodoService.findById(id);
            return ResponseEntity.ok(periodoDTO);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new PeriodoDTO());
        }
    }
>>>>>>> 83c591976b83e26e19e83635319b68d43aeb47ab

    @PostMapping
    public ResponseEntity<String> create(@RequestBody final PeriodoDTO periodoDTO){
        try {
            this.periodoService.create(periodoDTO);
            return ResponseEntity.ok("Cadastrado com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestParam("id") final Long id, @RequestBody final PeriodoDTO periodoDTO){
        try {
            this.periodoService.update(periodoDTO);
            return ResponseEntity.ok("Editado com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") final Long id){
        try {
            this.periodoService.delete(id);
            return ResponseEntity.ok("Excluido com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
