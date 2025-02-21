package com.example.service;

import com.example.common.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItems();

    Item getItemById(Integer itemId);
}
