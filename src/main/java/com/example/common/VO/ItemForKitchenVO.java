package com.example.common.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemForKitchenVO implements Serializable {
    private Integer id;
    private Integer tableNumber;
    private Integer orderId;
    private String name;
    private Integer quantity;
    private Integer status;
    private String remark;
}
