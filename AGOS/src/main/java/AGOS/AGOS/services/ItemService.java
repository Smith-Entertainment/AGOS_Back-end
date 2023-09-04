package AGOS.AGOS.services;

import AGOS.AGOS.entity.Item;
import AGOS.AGOS.repository.ItemRepository;
import AGOS.AGOS.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PeriodoRepository periodoRepository;

    @Transactional(rollbackFor = Exception.class)
    public Item findById(Long id){
        final Item item = this.itemRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Item não encontrado"));
        return item;
    }
    @Transactional(rollbackFor = Exception.class)
    public List<Item>findAll(){
        final List<Item> itemList= this.itemRepository.findAll();
        return itemList;
    }

    @Transactional(rollbackFor = Exception.class)
    public Item create(Item item){
        if (item.getNome() == null) {
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
    @Transactional(rollbackFor = Exception.class)
    public Item update(Item item) {
        Item itemBanco = this.itemRepository.findById(item.getId())
                .orElseThrow(() -> new RuntimeException("Não foi possível identificar o registro informado."));

        if (itemBanco.getNome() == null) {
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





}