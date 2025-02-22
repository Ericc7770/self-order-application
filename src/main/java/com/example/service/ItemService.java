package com.example.service;

import com.example.common.DTO.AddItemDTO;
import com.example.common.DTO.UpdateItemDTO;
import com.example.common.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItems();

    Item getItemById(Integer itemId);

    void addItem(AddItemDTO addItemDTO);

    void updateItem(UpdateItemDTO updateItemDTO);
}
