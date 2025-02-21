package com.example.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String itemName;
    private Integer quantity;
    private Double price;
    private String remark;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updateTime;
}
