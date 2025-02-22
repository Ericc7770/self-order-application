package com.example.common.DTO;

import lombok.Data;

@Data
public class AddItemDTO {
    private String name;
    private Integer categoryId;
    private Double price;
    private String image;
    private String description;
    private Integer createEmployee;
}
