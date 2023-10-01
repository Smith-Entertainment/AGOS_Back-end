package AGOS.agos.controller;

import AGOS.agos.dto.ItemDTO;
import AGOS.agos.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @GetMapping
    public ResponseEntity<ItemDTO> findById(@RequestParam("id") final Long id){
        try {
            ItemDTO itemDTO = this.itemService.findById(id);
            return ResponseEntity.ok(itemDTO);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new ItemDTO());
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody final ItemDTO itemDTO){
        try {
            this.itemService.create(itemDTO);
            return ResponseEntity.ok("Cadastrado com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestParam("id") final Long id, @RequestBody final ItemDTO itemDTO){
        try {
            this.itemService.update(   itemDTO);
            return ResponseEntity.ok("Editado com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") final Long id){
        try {
            this.itemService.delete(id);
            return ResponseEntity.ok("Excluido com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
