package com.example.controller;

import com.example.common.DTO.OrderItemDetail;
import com.example.common.DTO.PlaceOrderDTO;
import com.example.common.VO.ItemForKitchenVO;
import com.example.common.VO.ItemsForOrderVO;
import com.example.common.constant.GeneralConstant;
import com.example.common.constant.MessageConstant;
import com.example.common.entity.Item;
import com.example.common.exception.InvalidItemsException;
import com.example.common.exception.InvalidTableNumberException;
import com.example.common.result.Result;
import com.example.service.ItemService;
import com.example.service.OrderService;
import com.example.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    private final ItemService itemService;
    private final OrderService orderService;
    private final WebSocketServer webSocketServer;

    public OrderController(OrderService orderService, ItemService itemService, WebSocketServer webSocketServer) {
        this.orderService = orderService;
        this.itemService = itemService;
        this.webSocketServer = webSocketServer;
    }

    @GetMapping("/{tableNumber}")
    @Cacheable(cacheNames = "items")
    public Result<ItemsForOrderVO> getItems(@PathVariable Integer tableNumber){
        List<Item> items = itemService.getItems();
        ItemsForOrderVO itemsForOrderVO =
                ItemsForOrderVO
                .builder()
                .tableNumber(tableNumber)
                .Items(items)
                .build();
        return Result.success(itemsForOrderVO);
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
        List<ItemForKitchenVO> itemForKitchenVOS = orderService.createNewOrder(placeOrderDTO);
        webSocketServer.sendToAllClients(itemForKitchenVOS);
        return Result.success();
    }
}
