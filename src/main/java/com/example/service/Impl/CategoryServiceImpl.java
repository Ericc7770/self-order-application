package com.example.service.Impl;

import com.example.common.DTO.AddCategoryDTO;
import com.example.common.DTO.AddItemDTO;
import com.example.common.DTO.UpdateCategoryDTO;
import com.example.common.DTO.UpdateItemDTO;
import com.example.common.entity.Category;
import com.example.common.entity.Item;
import com.example.common.exception.CategoryNotFoundException;
import com.example.common.exception.InvalidStatusException;
import com.example.common.exception.ItemNotFoundException;
import com.example.mapper.CategoryMapper;
import com.example.mapper.ItemMapper;
import com.example.service.CategoryService;
import com.example.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void addCategory(AddCategoryDTO addCategoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryDTO, category);
        category.setUpdateEmployee(addCategoryDTO.getCreateEmployee());
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        category.setUpdateTime(now);
        categoryMapper.addCategory(category);
    }

    @Override
    public void updateCategory(UpdateCategoryDTO updateCategoryDTO) {
        Category category = categoryMapper.getCategoryById(updateCategoryDTO.getId());
        if (category == null){
            throw new CategoryNotFoundException("Category not found");
        }
        category = new Category();
        BeanUtils.copyProperties(updateCategoryDTO, category);
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        category.setUpdateTime(now);
        String newStatus = updateCategoryDTO.getNewStatus();
        if (newStatus != null){
            if (!newStatus.equals("enable") && !newStatus.equals("disable")){
                throw new InvalidStatusException("Invalid item status");
            } else {
                category.setStatus(newStatus.equals("enable")? category.ENABLED : category.DISABLED);
            }
        }
        categoryMapper.updateCategory(category);
    }

    @Override
    public List<Category> getCategories() {
        return categoryMapper.getCategories();
    }
}
