package AGOS.AGOS.controller;


import AGOS.AGOS.entity.Item;
import AGOS.AGOS.repository.ItemRepository;
import AGOS.AGOS.services.ItemService;
import com.electronwill.nightconfig.core.conversion.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<?>findById(@RequestParam("id") Long id){
        final Item item = this.itemService.findById(id);
        return ResponseEntity.ok(item);
    }
    @GetMapping
    public ResponseEntity<?>findByNome(@RequestParam("name") String name){
        final Item item = this.itemService.findByNome(name);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/list")
    public ResponseEntity<?>findAll(){
        final List<Item> itemList = this.itemService.findAll();
        return ResponseEntity.ok(itemList);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final Item item){
        try {
            this.itemService.create(item);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    @PutMapping
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Item item) {
        try {
            this.itemService.update(item);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }



}
