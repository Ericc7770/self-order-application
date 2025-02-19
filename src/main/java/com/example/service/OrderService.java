package com.example.service;

import com.example.common.DTO.PlaceOrderDTO;
import com.example.common.entity.Orders;
import com.example.common.entity.item;

import java.util.List;


public interface OrderService {
    List<item> getItems();

    Double getPriceById(Integer itemId);


    void createNewOrder(PlaceOrderDTO placeOrderDTO);
}
