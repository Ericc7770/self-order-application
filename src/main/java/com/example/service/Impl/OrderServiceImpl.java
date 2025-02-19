package com.example.service.Impl;

import com.example.common.DTO.OrderItemDetail;
import com.example.common.DTO.PlaceOrderDTO;
import com.example.common.entity.OrderDetail;
import com.example.common.entity.Orders;
import com.example.common.entity.item;
import com.example.mapper.OrderMapper;
import com.example.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<item> getItems() {
        List<item> items = orderMapper.getItems();
        return items;
    }

    @Override
    public Double getPriceById(Integer itemId) {
        Double price = orderMapper.getPriceById(itemId);
        return price;
    }

    @Override
    public void createNewOrder(PlaceOrderDTO placeOrderDTO) {
        List<OrderItemDetail> items = placeOrderDTO.getOrderDetails();
        Integer tableNumber = placeOrderDTO.getTableNumber();
        List<OrderDetail> orderDetails = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        Double sum = 0.0;
        for (OrderItemDetail item : items) {
            Double price = this.getPriceById(item.getItemId());
            sum += price * item.getQuantity();
            OrderDetail detail = OrderDetail.builder()
                    .itemId(item.getItemId())
                    .remark(item.getRemark())
                    .status(OrderDetail.CREATED)
                    .quantity(item.getQuantity())
                    .price(price)
                    .createTime(now)
                    .updateTime(now).build();
            orderDetails.add(detail);
        }
        Orders order = Orders.builder()
                .status(Orders.CREATED)
                .tableNumber(tableNumber)
                .totalAmount(sum)
                .paymentStatus(Orders.UNPAID)
                .createTime(now)
                .updateTime(now)
                .build();
        orderMapper.createOrder(order);
        Integer orderId = order.getId();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrderId(orderId);
            orderMapper.createOrderDetail(orderDetail);
        }
    }
}
