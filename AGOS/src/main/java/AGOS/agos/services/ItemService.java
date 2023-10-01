package AGOS.agos.services;

import AGOS.agos.dto.ItemDTO;
import AGOS.agos.entity.Item;
import AGOS.agos.repository.ItemRepository;
import AGOS.agos.repository.PeriodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Service
public class ItemService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PeriodoRepository periodoRepository;
     ItemDTO toItemDTO(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }
     Item toItem(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }
     void isNull(Long id){
        this.itemRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Item não encontrado"));
    }
     void validateItemDTO(ItemDTO itemDTO) {
        if(!itemDTO.getNome().matches("[a-zA-Z\\sç´~`^-]+")|| itemDTO.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do item não pode estar em branco");
        }else if (itemDTO.getObra() == null) {
            throw new IllegalArgumentException("Obra não pode ser nulo");
        }else if (Float.valueOf(itemDTO.getValorTotal()) == null) {
            throw new IllegalArgumentException("Valor total não pode ser nulo");
        }
    }
    public ItemDTO findById(Long id){
        isNull(id);
        final Item item = this.itemRepository.findById(id).orElse(null);
        return toItemDTO(item);
    }
    @Transactional(rollbackFor = Exception.class)
    public ItemDTO create(ItemDTO itemDTO){
        validateItemDTO(itemDTO);
        Optional<Item> itemExistente = itemRepository.findByNome(itemDTO.getNome());
        if (itemExistente.isPresent() && !itemExistente.get().getId().equals(itemDTO.getId())) {
            throw new IllegalArgumentException("Já existe um item cadastrado com esse nome: " + itemDTO.getNome());
        }
        return toItemDTO(itemRepository.save(toItem(itemDTO)));
    }
    @Transactional(rollbackFor = Exception.class)
    public ItemDTO update(ItemDTO itemDTO) {
        validateItemDTO(itemDTO);
        Optional<Item> itemExistente = itemRepository.findByNome(itemDTO.getNome());
        if (itemExistente.isPresent() && !itemExistente.get().getId().equals(itemDTO.getId())) {
            throw new IllegalArgumentException("Já existe um item cadastrado com esse nome: " + itemDTO.getNome());
        }
        return toItemDTO(itemRepository.save(toItem(itemDTO)));
    }
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        isNull(id);
        itemRepository.deleteById(id);
    }

}
