package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.ValorDTO;
import AGOS.AGOS.services.ValorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/valores")
public class ValorController {
    @Autowired
    private ValorService valorService;
    @GetMapping
    public ResponseEntity<ValorDTO> findById(@RequestParam("id") final Long id){
        try {
            ValorDTO valorDTO = this.valorService.findById(id);
            return ResponseEntity.ok(valorDTO);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new ValorDTO());
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody final ValorDTO valorDTO){
        try {
            this.valorService.create(valorDTO);
            return ResponseEntity.ok("Cadastrado com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestParam("id") final Long id, @RequestBody final ValorDTO valorDTO){
        try {
            this.valorService.update(   valorDTO);
            return ResponseEntity.ok("Editado com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") final Long id){
        try {
            this.valorService.delete(id);
            return ResponseEntity.ok("Excluido com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
