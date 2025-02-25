package com.example.service.Impl;

import com.example.common.DTO.ChangeOrderDTO;
import com.example.common.DTO.OrderItemDetail;
import com.example.common.DTO.PlaceOrderDTO;
import com.example.common.DTO.UpdateItemForKitchenDTO;
import com.example.common.VO.ItemForKitchenVO;
import com.example.common.constant.OperationConstant;
import com.example.common.entity.Item;
import com.example.common.entity.OrderDetail;
import com.example.common.entity.Orders;
import com.example.common.enums.PaymentType;
import com.example.common.exception.InvalidOperationException;
import com.example.common.exception.InvalidPaymentTypeException;
import com.example.mapper.ItemMapper;
import com.example.mapper.OrderMapper;
import com.example.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;


    public OrderServiceImpl(OrderMapper orderMapper, ItemMapper itemMapper) {
        this.orderMapper = orderMapper;
        this.itemMapper = itemMapper;
    }



    @Override
    public List<ItemForKitchenVO> createNewOrder(PlaceOrderDTO placeOrderDTO) {
        List<OrderItemDetail> items = placeOrderDTO.getOrderDetails();
        Integer tableNumber = placeOrderDTO.getTableNumber();
        List<OrderDetail> orderDetails = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        Double sum = 0.0;
        for (OrderItemDetail item : items) {
            Item temp = itemMapper.getItemById(item.getItemId());
            Double price = temp.getPrice();
            String itemName = temp.getName();
            sum += price * item.getQuantity();
            OrderDetail detail = OrderDetail.builder()
                    .itemId(item.getItemId())
                    .remark(item.getRemark())
                    .status(OrderDetail.CREATED)
                    .quantity(item.getQuantity())
                    .price(price)
                    .itemName(itemName)
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
        List<ItemForKitchenVO> itemForKitchenVOList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrderId(orderId);
            orderMapper.createOrderDetail(orderDetail);
            ItemForKitchenVO item = ItemForKitchenVO.builder()
                    .id(orderDetail.getId())
                    .name(orderDetail.getItemName())
                    .tableNumber(tableNumber)
                    .status(OrderDetail.CREATED)
                    .remark(orderDetail.getRemark())
                    .orderId(orderId)
                    .quantity(orderDetail.getQuantity())
                    .build();
            itemForKitchenVOList.add(item);
        }
        return itemForKitchenVOList;
    }

    @Override
    public List<Orders> getOrdersForToday() {
        List<Orders> ordersForToday= orderMapper.getOrdersForToday();
        return ordersForToday;
    }

    @Override
    public List<Orders> getHistoryOrders() {
        List<Orders> orders= orderMapper.getHistoryOrders();
        return orders;
    }

    @Override
    public List<OrderDetail> getOrderDetails(int i) {
        List<OrderDetail> details = orderMapper.getOrderDetails(i);
        return details;
    }

    @Override
    public void changeStatus(ChangeOrderDTO changeOrderDTO) {
        String orderId = changeOrderDTO.getOrderId();
        String operation = changeOrderDTO.getOperation();
        if (!operation.equals(OperationConstant.FULFILL) && !operation.equals(OperationConstant.CANCEL)){
            throw new InvalidOperationException("Invalid operation");
        }
        if (operation.equals(OperationConstant.FULFILL) && !PaymentType.isValidPaymentType(changeOrderDTO.getPaidBy())){
            throw new InvalidPaymentTypeException("Invalid payment type");
        }
        Orders orders = Orders.builder()
                .id(Integer.valueOf(orderId))
                .status(operation.equals(OperationConstant.FULFILL) ? Orders.FULFILLED : Orders.CANCELLED)
                .updateTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        if (operation.equals(OperationConstant.FULFILL)){
            String paidBy = changeOrderDTO.getPaidBy();
            orders.setPaymentStatus(Orders.PAID);
            orders.setPaymentMethod(paidBy.equals("credit")? Orders.CREDIT: (paidBy.equals("debit")? Orders.DEBIT : Orders.CASH));
        }
        orderMapper.updateOrder(orders);
        if (operation.equals(OperationConstant.CANCEL)){
            OrderDetail detail = OrderDetail.builder()
                    .orderId(Integer.valueOf(orderId))
                    .status(OrderDetail.CANCELLED)
                    .updateTime(LocalDateTime.now(ZoneOffset.UTC))
                    .build();
            orderMapper.updateOrderDetails(detail);
        }
    }

    @Override
    public void changeDishStatus(UpdateItemForKitchenDTO updateItemForKitchenDTO) {
        String newStatus = updateItemForKitchenDTO.getNewStatus();
        if (!newStatus.equals("fulfill") && !newStatus.equals("cancel")){
            throw new InvalidOperationException("Invalid new status");
        }
        OrderDetail detail = OrderDetail.builder()
                .id(updateItemForKitchenDTO.getOrderDetailId())
                .status(newStatus.equals("fulfill") ? OrderDetail.FULFILLED : OrderDetail.CANCELLED).build();
        orderMapper.changeDishStatus(detail);
    }
}
