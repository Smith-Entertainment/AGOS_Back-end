package AGOS.AGOS.services;

import AGOS.AGOS.dto.ItemDTO;
import AGOS.AGOS.entity.Item;
import AGOS.AGOS.repository.ItemRepository;
import AGOS.AGOS.repository.PeriodoRepository;
import AGOS.AGOS.repository.ValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ValorRepository cronogramaRepository;
    @Autowired
    private PeriodoRepository periodoRepository;

    @Transactional(rollbackFor = Exception.class)
    public ItemDTO findById(Long id){
        final Item item = this.itemRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Item não encontrado"));

        return convertToDTO(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ItemDTO> findAll(){
        final List<Item> itemList= this.itemRepository.findAll();

        return itemList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public ItemDTO create(ItemDTO itemDTO){
        validateItemDTO(itemDTO);
        Item item = convertToEntity(itemDTO);
        Optional<Item> itemExistente = itemRepository.findByNome(item.getNome());
        if (itemExistente.isPresent() && !itemExistente.get().getId().equals(item.getId())) {
            throw new IllegalArgumentException("Já existe um item cadastrado com esse nome: " + item.getNome());
        }
        item = itemRepository.save(item);
        return convertToDTO(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public ItemDTO update(ItemDTO itemDTO) {
        validateItemDTO(itemDTO);
        Item item = convertToEntity(itemDTO);
        Optional<Item> itemExistente = itemRepository.findByNome(item.getNome());
        if (itemExistente.isPresent() && !itemExistente.get().getId().equals(item.getId())) {
            throw new IllegalArgumentException("Já existe um item cadastrado com esse nome: " + item.getNome());
        }
        item = itemRepository.save(item);

        return convertToDTO(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        final Item item = this.itemRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Item não encontrado"));
        this.itemRepository.delete(item);
    }
    private ItemDTO convertToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setNome(item.getNome());
        itemDTO.setValorTotal(item.getValorTotal());
        itemDTO.setObra(item.getObra());
        return itemDTO;
    }
    private Item convertToEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setNome(itemDTO.getNome());
        item.setValorTotal(itemDTO.getValorTotal());
        item.setObra(itemDTO.getObra());
        return item;
    }

    private void validateItemDTO(ItemDTO itemDTO) {
        if(!itemDTO.getNome().matches("[a-zA-Z\\sç´~`^-]+")|| itemDTO.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do item não pode estar em branco");
        }else if (itemDTO.getObra() == null) {
            throw new IllegalArgumentException("Obra não pode ser nulo");
        }else if (Float.valueOf(itemDTO.getValorTotal()) == null) {
            throw new IllegalArgumentException("Valor total não pode ser nulo");
        }
    }
}
