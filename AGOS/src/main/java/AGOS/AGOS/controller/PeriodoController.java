package AGOS.AGOS.controller;

import AGOS.AGOS.DTO.PeriodoDTO;
import AGOS.AGOS.services.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periodos")
public class PeriodoController {
    @Autowired
    private  PeriodoService periodoService;
    @GetMapping
    public ResponseEntity<PeriodoDTO> findById (@RequestParam Long id) {
        try {
            PeriodoDTO periodoDTO = periodoService.findById(id);
            return new ResponseEntity<>(periodoDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<PeriodoDTO>> findAll (@RequestParam Long id) {
        try {
            List<PeriodoDTO> periodoDTOList = periodoService.findAll(id);
            return new ResponseEntity<>(periodoDTOList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PeriodoDTO> create (@RequestBody PeriodoDTO periodoDTO) {
        try {
            PeriodoDTO createdPeriodoDTO = periodoService.create(periodoDTO);
            return new ResponseEntity<>(createdPeriodoDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<PeriodoDTO> update (@RequestParam Long id, @RequestBody PeriodoDTO periodoDTO) {
        try {
            periodoDTO.setId(id);
            PeriodoDTO updatedPeriodoDTO = periodoService.update(periodoDTO);
            return new ResponseEntity<>(updatedPeriodoDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping
    public ResponseEntity<Void> delete (@RequestParam Long id) {
        try {
            periodoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
