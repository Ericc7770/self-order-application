package com.example.service;

import com.example.common.DTO.ChangeOrderDTO;
import com.example.common.DTO.PlaceOrderDTO;
import com.example.common.entity.Item;
import com.example.common.entity.OrderDetail;
import com.example.common.entity.Orders;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface OrderService {

    void createNewOrder(PlaceOrderDTO placeOrderDTO);

    List<Orders> getOrdersForToday();

    List<Orders> getHistoryOrders();


    List<OrderDetail> getOrderDetails(int i);

    void changeStatus(ChangeOrderDTO changeOrderDTO);
}
