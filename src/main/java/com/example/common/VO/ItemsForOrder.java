package com.example.common.VO;

import com.example.common.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemsForOrder implements Serializable {
    private Integer tableNumber;
    private List<Item> Items;
}
