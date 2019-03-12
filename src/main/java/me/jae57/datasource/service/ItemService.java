package me.jae57.datasource.service;

import me.jae57.datasource.dto.ItemDto;
import me.jae57.datasource.model.Item;
import me.jae57.datasource.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepostiroy){
        this.itemRepository = itemRepostiroy;
    }

    public List<ItemDto> getAllItems() {
        List<Item> items=itemRepository.getAllItems();
        List<ItemDto> itemDtos=new ArrayList<>();
        for(Item item : items){
            ItemDto itemDto = new ItemDto(item.getId(), item.getProduct_name(), item.getCompany(), item.getCategory());
            System.out.println(item.getId());
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    public ItemDto getItem(int itemId){
        Item item = itemRepository.getItem(itemId);
        ItemDto itemDto = new ItemDto(item.getId(),item.getProduct_name(),item.getCompany(),item.getCategory());
        return itemDto;
    }

}
