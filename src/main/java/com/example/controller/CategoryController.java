package com.example.controller;

import com.example.common.DTO.AddCategoryDTO;
import com.example.common.DTO.UpdateCategoryDTO;
import com.example.common.entity.Category;
import com.example.common.result.Result;
import com.example.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    private final RedisTemplate redisTemplate;

    public CategoryController(CategoryService categoryService, RedisTemplate redisTemplate) {
        this.categoryService = categoryService;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/order/category")
    @Cacheable(cacheNames = "categories")
    public Result<List<Category>> getCategories(){
        List<Category> categories = categoryService.getCategories();
        return Result.success(categories);
    }
    @PostMapping("/admin/category/add")
    @CacheEvict(cacheNames = "categories", allEntries = true)
    public Result addNewCategory(@RequestBody AddCategoryDTO addCategoryDTO){
        categoryService.addCategory(addCategoryDTO);
        return Result.success();
    }

    @PostMapping("/admin/category/update")
    @CacheEvict(cacheNames = "categories", allEntries = true)
    public Result updateCategory(@RequestBody UpdateCategoryDTO updateCategoryDTO){
        categoryService.updateCategory(updateCategoryDTO);
        return Result.success();
    }
}
