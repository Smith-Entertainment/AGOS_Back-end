package AGOS.AGOS.controllers;

import AGOS.AGOS.DTO.ItemDTO;
import AGOS.AGOS.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<ItemDTO> findById (@RequestParam Long id) {
        try {
            ItemDTO itemDTO = itemService.findById(id);
            return new ResponseEntity<>(itemDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> findAll() {
        try {
            List<ItemDTO> itemDTOList = itemService.findAll();
            return new ResponseEntity<>(itemDTOList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ItemDTO> create (@RequestBody ItemDTO itemDTO) {
        try {
            ItemDTO createdItemDTO = itemService.create(itemDTO);
            return new ResponseEntity<>(createdItemDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ItemDTO> update (@RequestParam Long id, @RequestBody ItemDTO itemDTO) {
        try {
            itemDTO.setId(id);
            ItemDTO updatedItemDTO = itemService.update(itemDTO);
            return new ResponseEntity<>(updatedItemDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> delete (@RequestParam Long id) {
        try {
            itemService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
