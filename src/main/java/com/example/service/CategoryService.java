package com.example.service;

import com.example.common.DTO.AddCategoryDTO;
import com.example.common.DTO.AddItemDTO;
import com.example.common.DTO.UpdateCategoryDTO;
import com.example.common.DTO.UpdateItemDTO;
import com.example.common.entity.Category;
import com.example.common.entity.Item;

import java.util.List;

public interface CategoryService {

    void addCategory(AddCategoryDTO addCategoryDTO);

    void updateCategory(UpdateCategoryDTO updateCategoryDTO);

    List<Category> getCategories();
}
