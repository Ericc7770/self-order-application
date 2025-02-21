package com.example.common.DTO;

import lombok.Data;

@Data
public class ChangeOrderDTO {
    private String orderId;
    private String operation;
    private String paidBy;
}
