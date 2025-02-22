package com.example.mapper;

import com.example.common.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM category WHERE id = #{id}")
    Category getCategoryById(Integer id);
    void addCategory(Category category);

    void updateCategory(Category category);

    @Select("SELECT * FROM category")
    List<Category> getCategories();
}
