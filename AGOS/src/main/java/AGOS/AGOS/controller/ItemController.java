package AGOS.AGOS.controller;


import AGOS.AGOS.entity.Item;
import AGOS.AGOS.repository.ItemRepository;
import AGOS.AGOS.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/obra/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Item item = this.itemRepository.findById(id).orElse(null);

        return item == null
                ? ResponseEntity.badRequest().body("Item não encontrado")
                : ResponseEntity.ok(item);
    }


}
