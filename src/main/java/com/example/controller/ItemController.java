package com.example.controller;

import com.example.common.DTO.AddItemDTO;
import com.example.common.DTO.UpdateItemDTO;
import com.example.common.result.Result;
import com.example.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items/add")
    @CacheEvict(cacheNames = "items", allEntries = true)
    public Result addNewItem(@RequestBody AddItemDTO addItemDTO){
        itemService.addItem(addItemDTO);
        return Result.success();
    }

    @PostMapping("/items/update")
    @CacheEvict(cacheNames = "items", allEntries = true)
    public Result updateItem(@RequestBody UpdateItemDTO updateItemDTO){
        itemService.updateItem(updateItemDTO);
        return Result.success();
    }
}
