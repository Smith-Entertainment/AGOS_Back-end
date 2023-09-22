package AGOS.agos.controller;

import AGOS.agos.dto.ValorDTO;
import AGOS.agos.services.ValorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/valores")
public class ValorController {
    @Autowired
    private ValorService valorService;
    @GetMapping
    public ResponseEntity<ValorDTO> findById (@RequestParam Long id) {
        try {
            ValorDTO valorDTO = valorService.findById(id);
            return new ResponseEntity<>(valorDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ValorDTO> create (@RequestBody ValorDTO valorDTO) {
        try {
            ValorDTO createdValorDTO = valorService.create(valorDTO);
            return new ResponseEntity<>(createdValorDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<ValorDTO> update (@RequestParam Long id, @RequestBody ValorDTO valorDTO) {
        try {
            valorDTO.setId(id);
            ValorDTO updatedValorDTO = valorService.update(valorDTO);
            return new ResponseEntity<>(updatedValorDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping
    public ResponseEntity<Void> delete (@RequestParam Long id) {
        try {
            valorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
