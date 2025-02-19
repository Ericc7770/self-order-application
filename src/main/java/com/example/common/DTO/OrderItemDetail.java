package com.example.common.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderItemDetail implements Serializable {
    private Integer itemId;
    private Integer quantity;
    private String remark;
}
