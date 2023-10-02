package AGOS.AGOS.controller;


import AGOS.AGOS.DTO.ItemDTO;
import AGOS.AGOS.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<?>findById(@RequestParam("id") final Long id){
        try {
            final ItemDTO item = this.itemService.findById(id);
            return ResponseEntity.ok(item);
        } catch (IllegalArgumentException e) {
             return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
         }
    }

    @GetMapping("/list")
    public ResponseEntity<?>findAll(){
        try {
        final List<ItemDTO> itemList = this.itemService.findAll();
        return ResponseEntity.ok(itemList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final ItemDTO item){
        try {
            this.itemService.create(item);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    @PutMapping


    public ResponseEntity<String> update(@RequestParam("id") Long id, @RequestBody ItemDTO item) {
        try {
            this.itemService.update(item);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }



}
