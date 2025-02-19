package com.example.controller;

import com.example.common.DTO.OrderItemDetail;
import com.example.common.DTO.PlaceOrderDTO;
import com.example.common.VO.ItemsForOrder;
import com.example.common.constant.GeneralConstant;
import com.example.common.constant.MessageConstant;
import com.example.common.entity.OrderDetail;
import com.example.common.entity.Orders;
import com.example.common.entity.item;
import com.example.common.exception.InvalidItemsException;
import com.example.common.exception.InvalidTableNumberException;
import com.example.common.result.Result;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @PostMapping("/place")
    public Result placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO){
        List<OrderItemDetail> items = placeOrderDTO.getOrderDetails();
        Integer tableNumber = placeOrderDTO.getTableNumber();
        if (items == null || items.size() == 0){
            throw new InvalidItemsException(MessageConstant.INVALID_ITEM);
        }
        if (tableNumber < 1 || tableNumber > GeneralConstant.TABLE_COUNT){
            throw new InvalidTableNumberException(MessageConstant.INVALID_TABLE_NUMBER);
        }
        orderService.createNewOrder(placeOrderDTO);
        return Result.success();
    }
}
