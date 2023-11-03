package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.ObraDTO;
import AGOS.AGOS.services.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/obra")
public class ObraController {
    @Autowired
    private ObraService obraService;

    @GetMapping
    public ResponseEntity<ObraDTO> findById(@RequestParam("id") final Long id) {
        try {
            final ObraDTO obraDTO = this.obraService.findById(id);
            return ResponseEntity.ok(obraDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ObraDTO());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ObraDTO>> findAll() {
        try {
            final List<ObraDTO> obrasDTO = this.obraService.findAll();
            return ResponseEntity.ok(obrasDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody final ObraDTO obraDTO) {
        try {
            this.obraService.create(obraDTO);
            return ResponseEntity.ok("Obra cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestParam("id") final Long id, @RequestBody final ObraDTO obraDTO) {
        try {
            this.obraService.update(id, obraDTO);
            return ResponseEntity.ok("Obra editada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") final Long id) {
        try {
            this.obraService.delete(id);
            return ResponseEntity.ok("Obra excluída com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}