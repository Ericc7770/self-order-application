package com.example.common.DTO;

import lombok.Data;

@Data
public class UpdateItemDTO {
    private Integer id;
    private String name;
    private Integer categoryId;
    private Double price;
    private String image;
    private String description;
    private Integer updateEmployee;
    private String newStatus;
}
