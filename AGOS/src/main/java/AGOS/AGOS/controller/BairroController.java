package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.BairroDTO;
import AGOS.AGOS.entity.Bairro;
import AGOS.AGOS.services.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/bairro")
public class BairroController {
    @Autowired
    private BairroService bairroService;

    @GetMapping
    public ResponseEntity<BairroDTO> findById(@RequestParam("id") final Long id){
        try {
            BairroDTO bairroDTO = this.bairroService.findById(id);
            return ResponseEntity.ok(bairroDTO);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new BairroDTO());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<BairroDTO>> findAll(){
        try {
            List<BairroDTO> bairrosDTO = this.bairroService.findAll();
            return ResponseEntity.ok(bairrosDTO);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody final BairroDTO bairroDTO){
        try {
            this.bairroService.create(bairroDTO);
            return ResponseEntity.ok("Bairro cadastrado com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestParam("id") final Long id, @RequestBody final BairroDTO bairroDTO){
        try {
            this.bairroService.update(id, bairroDTO);
            return ResponseEntity.ok("Bairro editado com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") final Long id){
        try {
            this.bairroService.delete(id);
            return ResponseEntity.ok("Bairro excluido com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}