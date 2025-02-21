package com.example.service.Impl;

import com.example.common.entity.Item;
import com.example.mapper.ItemMapper;
import com.example.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }
    @Override
    public List<Item> getItems() {
        List<Item> Items = itemMapper.getItems();
        return Items;
    }

    @Override
    public Item getItemById(Integer itemId) {
        Item item = itemMapper.getItemById(itemId);
        return item;
    }
}
