package com.example.mapper;

import com.example.common.entity.item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from item")
    List<item> getItems();
}
