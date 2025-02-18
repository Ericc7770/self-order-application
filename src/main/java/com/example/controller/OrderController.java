package com.example.controller;

import com.example.common.VO.ItemsForOrder;
import com.example.common.entity.item;
import com.example.common.result.Result;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{tableNumber}")
    public Result<ItemsForOrder> getItems(@PathVariable Integer tableNumber){
        List<item> items = orderService.getItems();
        ItemsForOrder itemsForOrder =
                ItemsForOrder
                .builder()
                .tableNumber(tableNumber)
                .items(items)
                .build();
        return Result.success(itemsForOrder);
    }
}
