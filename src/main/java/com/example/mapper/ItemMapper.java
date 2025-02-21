package com.example.mapper;

import com.example.common.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemMapper {
    @Select("SELECT * FROM item")
    List<Item> getItems();

    @Select("SELECT * FROM item WHERE id = #{itemId}")
    Item getItemById(Integer itemId);

}
