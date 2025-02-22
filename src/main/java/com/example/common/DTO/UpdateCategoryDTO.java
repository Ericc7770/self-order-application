package com.example.common.DTO;

import lombok.Data;

@Data
public class UpdateCategoryDTO {
    private Integer id;
    private String name;
    private String newStatus;
    private Integer updateEmployee;
}
