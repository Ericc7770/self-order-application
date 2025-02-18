package com.example.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class item implements Serializable {
    private Integer id;
    private String name;
    private Integer categoryId;
    private BigDecimal price;
    private String image;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer createEmployee;
    private Integer updateEmployee;
}
