package AGOS.AGOS.services;

import AGOS.AGOS.entity.Cronograma;
import AGOS.AGOS.entity.Item;
import AGOS.AGOS.repository.CronogramaRepository;
import AGOS.AGOS.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CronogramaRepository cronogramaRepository;


    @Transactional
    public Item newItem(Item item){

        Optional<Item> existingItem = itemRepository.findByNome(item.getNome());
        if (existingItem.isPresent()) {
            throw new IllegalArgumentException ("Já existe um item cadastrado com esse nome: " + item.getNome());
        } else if (item.getNome() == null || !item.getNome().matches("[a-zA-Z\\sç´~`^-]+")) {
            throw new IllegalArgumentException("O nome da item deve conter somente letras");
        }

        return  itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }
        Optional<Item> itemExistente = itemRepository.findByNome(item.getNome());

        if (itemExistente.isPresent() && itemExistente.get().getId() != item.getId()) {
            throw new IllegalArgumentException("Já existe um item cadastrado com esse nome: " + item.getNome());
        } else if (item.getNome() == null || !item.getNome().matches("[a-zA-Z\\sç´~`^-]+")) {
            throw new IllegalArgumentException("O nome da item deve conter somente letras");
        }

        return itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(Long id) {
        final Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new RuntimeException("Não foi possível identificar o registro informado.");
        }
        if (cronogramaRepository.existsByItem(item)) {
            throw new RuntimeException("Não é possível excluir um item presente em um cronograma.");
        }
        itemRepository.delete(item);
    }



}
