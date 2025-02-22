package com.example.service.Impl;

import com.example.common.DTO.AddItemDTO;
import com.example.common.DTO.UpdateItemDTO;
import com.example.common.entity.Item;
import com.example.common.exception.InvalidStatusException;
import com.example.common.exception.ItemNotFoundException;
import com.example.mapper.ItemMapper;
import com.example.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

    @Override
    public void addItem(AddItemDTO addItemDTO) {
        Item item = new Item();
        BeanUtils.copyProperties(addItemDTO, item);
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        item.setUpdateTime(now);
        item.setCreateTime(now);
        item.setUpdateEmployee(addItemDTO.getCreateEmployee());
        itemMapper.addItem(item);
    }

    @Override
    public void updateItem(UpdateItemDTO updateItemDTO) {
        Item item = itemMapper.getItemById(updateItemDTO.getId());
        if (item == null){
            throw new ItemNotFoundException("Item not found");
        }
        item = new Item();
        BeanUtils.copyProperties(updateItemDTO, item);
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        item.setUpdateTime(now);
        String newStatus = updateItemDTO.getNewStatus();
        if (newStatus != null){
            if (!newStatus.equals("enable") && !newStatus.equals("disable")){
                throw new InvalidStatusException("Invalid item status");
            } else {
                item.setStatus(newStatus.equals("enable")? Item.ENABLED : Item.DISABLED);
            }
        }
        itemMapper.updateItem(item);
    }
}
