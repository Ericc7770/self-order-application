package com.example.mapper;

import com.example.common.entity.OrderDetail;
import com.example.common.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    void createOrder(Orders order);

    void createOrderDetail(OrderDetail orderDetail);

    @Select("SELECT * FROM orders WHERE DATE(create_time) = CURDATE()")
    List<Orders> getOrdersForToday();
    @Select("SELECT * FROM orders")
    List<Orders> getHistoryOrders();

    @Select("SELECT * FROM order_details WHERE order_id = #{i}")
    List<OrderDetail> getOrderDetails(int i);

    void updateOrder(Orders orders);

    void updateOrderDetails(OrderDetail detail);
}
