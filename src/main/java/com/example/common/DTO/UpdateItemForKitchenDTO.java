package com.example.common.DTO;

import lombok.Data;

@Data
public class UpdateItemForKitchenDTO {
    private Integer orderDetailId;
    private String newStatus;
}
