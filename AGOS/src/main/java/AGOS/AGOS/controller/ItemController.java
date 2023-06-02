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

@Controller
@RequestMapping("/obra/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id")final Long id){
        final Item item = this.itemRepository.findById(id).orElse(null);

        return item == null
                ? ResponseEntity.badRequest().body("Item não encontrado")
                : ResponseEntity.ok(item);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll(){
        final List<Item> itens = this.itemRepository.findAll();

        return ResponseEntity.ok(itens);
    }
    @GetMapping("/buscarItensPorObra/{idObra}")
    public ResponseEntity<?> buscarItensPorObra(@PathVariable("idObra") Long idObra) {
        List<Item> itens = itemRepository.findByObraIdId(idObra);

        return ResponseEntity.ok(itens);
    }



    @PostMapping
    public ResponseEntity<?> newItem(@RequestBody final Item item){
        try {
            this.itemService.newItem(item);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody Item item) {
        try {
            Item itemBanco = this.itemRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Não foi possível identificar o registro informado."));
            if (!itemBanco.getNome().equals(item.getNome())) {
                Item itemDuplicado = this.itemRepository.findByNome(item.getNome()).orElse(null);
                if (itemDuplicado != null && !itemDuplicado.getId().equals(itemBanco.getId())) {
                    throw new IllegalArgumentException("Já existe um item cadastrado com esse nome: " + item.getNome());
                }
            }
            itemBanco.setNome(item.getNome());
            this.itemService.updateItem(itemBanco);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") final Long id){
        final Item itemBanco = this.itemRepository.findById(id).orElse(null);

        this.itemService.deleteItem(id);
        return ResponseEntity.ok("Registro Excluido com Sucesso");
    }

}