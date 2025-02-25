package com.example.controller;

import com.example.common.DTO.ChangeOrderDTO;
import com.example.common.DTO.UpdateItemForKitchenDTO;
import com.example.common.entity.OrderDetail;
import com.example.common.entity.Orders;
import com.example.common.result.Result;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
public class OrderManagementController {

    private final OrderService orderService;

    public OrderManagementController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public Result<List<Orders>> getOrders(){
        List<Orders> ordersForToday= orderService.getOrdersForToday();
        return Result.success(ordersForToday);
    }

    @GetMapping("/historyOrders")
    public Result<List<Orders>> getHistoryOrders(){
        List<Orders> orders= orderService.getHistoryOrders();
        return Result.success(orders);
    }

    @GetMapping("/orders/{orderId}")
    public Result<List<OrderDetail>> getOrderDetails(@PathVariable String orderId){
        List<OrderDetail> details= orderService.getOrderDetails(Integer.parseInt(orderId));
        return Result.success(details);
    }
    @PostMapping("/orders/manage")
    public Result changeOrderStatus(@RequestBody ChangeOrderDTO changeOrderDTO){
        orderService.changeStatus(changeOrderDTO);
        return Result.success();
    }
    @PostMapping("/orders/manageDish")
    public Result changeDishStatus(@RequestBody UpdateItemForKitchenDTO updateItemForKitchenDTO){
        orderService.changeDishStatus(updateItemForKitchenDTO);
        return Result.success();
    }
}
