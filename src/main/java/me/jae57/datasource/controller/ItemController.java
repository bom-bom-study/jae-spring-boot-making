package me.jae57.datasource.controller;

import me.jae57.datasource.dto.ItemDto;
import me.jae57.datasource.model.Item;
import me.jae57.datasource.repository.ItemRepository;
import me.jae57.datasource.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDto>> getAllItems(){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getAllItems());
    }

    @GetMapping("/items/{item-id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable("item-id") int itemId){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getItem(itemId));
    }
    /*
    @PostMapping("/addItem")
    @ResponseBody
    public String addItem(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("category") String category){
        if(itemRepo.addItem(id,name,category) >=1){
            return "Item Added Successfully";
        }else{
            return "Something went wrong!";
        }
    }

    @RequestMapping("/deleteItem")
    @ResponseBody
    public String deleteItem(@RequestParam("itemId") int itemId){
        if(itemRepo.deleteItem(itemId) >= 1){
            return "Item Deleted Successfully";
        }else{
            return "Something went wrong!";
        }
    }*/

}
