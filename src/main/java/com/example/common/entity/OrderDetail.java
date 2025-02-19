package com.example.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail implements Serializable {
    public static final Integer CREATED = 1;
    public static final Integer FULFILLED = 2;
    public static final Integer CANCELLED = 0;

    private Integer id;
    private Integer orderId;
    private Integer itemId;
    private Integer quantity;
    private Double price;
    private String remark;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
