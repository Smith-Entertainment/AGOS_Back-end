package AGOS.AGOS.services;

import AGOS.AGOS.DTO.ItemDTO;
import AGOS.AGOS.entity.Item;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Item.class)
class ItemServiceTest {
    @InjectMocks
    ItemService itemService;
    @Mock
    ItemRepository itemRepository;
    @Mock
    ModelMapper modelMapper;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeEach
    void injectData() {
        Item item = new Item();
        item.setId(1L);
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
    }
    @Test
    void TestServiceFindById01() {
        Long id = 1L;
        Item item = new Item();
        item.setId(id);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(id);
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));
        when(modelMapper.map(item, ItemDTO.class)).thenReturn(itemDTO);
        ItemDTO result = itemService.findById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void TestServiceFindById02() {
        Long id = 1L;
        when(itemRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> itemService.findById(id));
    }

    @Test
    void TestServiceCreated01() {
        ItemDTO itemDTO = createValidItemDTO();
        Item item = itemService.toItem(itemDTO);
        when(modelMapper.map(item, ItemDTO.class)).thenReturn(itemDTO);
        when(itemRepository.save(any(Item.class))).thenReturn(item);
        ItemDTO result = itemService.create(itemDTO);
        assertNotNull(result);
    }

    @Test
    void TestServiceUpdate01() {
        Long id = 1L;
        ItemDTO itemDTO = createValidItemDTO();
        itemDTO.setId(id);
        Item item = itemService.toItem(itemDTO);

        when(itemRepository.save(any(Item.class))).thenReturn(item);

        when(modelMapper.map(item, ItemDTO.class)).thenReturn(itemDTO);
        ItemDTO result = itemService.update(itemDTO);
        Assertions.assertNotNull(result);
        assertEquals(id, result.getId());
    }


    @Test
    void TestServiceDelete01() {
        Long id = 1L;
        Item item = new Item();
        item.setId(id);
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        assertDoesNotThrow(() -> itemService.delete(id));
    }

    @Test
    void TestServiceDelete02() {
        Long id = 1L;
        when(itemRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> itemService.delete(id));
    }


    private ItemDTO createValidItemDTO() {
        ItemDTO itemDTO = new ItemDTO();
        Obra obra = new Obra();
        itemDTO.setNome("Item Name");
        itemDTO.setObra(obra);
        itemDTO.setValorTotal(100.0f);
        return itemDTO;
    }

}