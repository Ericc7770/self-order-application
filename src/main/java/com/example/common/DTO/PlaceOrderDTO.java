package com.example.common.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PlaceOrderDTO implements Serializable {
    private Integer tableNumber;
    private List<OrderItemDetail> orderDetails;
}
