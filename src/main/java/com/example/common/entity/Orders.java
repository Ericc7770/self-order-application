package com.example.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {
    public static final Integer CREATED = 1;
    public static final Integer FULFILLED = 2;
    public static final Integer CANCELLED = 0;

    public static final Integer UNPAID = 1;
    public static final Integer PAID = 2;

    public static final Integer CASH = 1;
    public static final Integer DEBIT = 2;
    public static final Integer CREDIT = 0;

    private Integer id;
    private Integer tableNumber;
    private Integer status;
    private Double totalAmount;
    private Integer paymentStatus;
    private Integer paymentMethod;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updateTime;
}
