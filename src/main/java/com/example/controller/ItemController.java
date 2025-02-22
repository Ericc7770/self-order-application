package com.example.controller;

import com.example.common.DTO.AddItemDTO;
import com.example.common.DTO.ChangeOrderDTO;
import com.example.common.DTO.UpdateItemDTO;
import com.example.common.entity.OrderDetail;
import com.example.common.entity.Orders;
import com.example.common.result.Result;
import com.example.service.ItemService;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items/add")
    public Result addNewItem(@RequestBody AddItemDTO addItemDTO){
        itemService.addItem(addItemDTO);
        return Result.success();
    }

    @PostMapping("/items/update")
    public Result updateItem(@RequestBody UpdateItemDTO updateItemDTO){
        itemService.updateItem(updateItemDTO);
        return Result.success();
    }
}
