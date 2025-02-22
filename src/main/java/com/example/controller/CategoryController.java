package com.example.controller;

import com.example.common.DTO.AddCategoryDTO;
import com.example.common.DTO.AddItemDTO;
import com.example.common.DTO.UpdateCategoryDTO;
import com.example.common.DTO.UpdateItemDTO;
import com.example.common.result.Result;
import com.example.service.CategoryService;
import com.example.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public Result getCategories(){
        return Result.success(categoryService.getCategories());
    }
    @PostMapping("/category/add")
    public Result addNewCategory(@RequestBody AddCategoryDTO addCategoryDTO){
        categoryService.addCategory(addCategoryDTO);
        return Result.success();
    }

    @PostMapping("/category/update")
    public Result updateCategory(@RequestBody UpdateCategoryDTO updateCategoryDTO){
        categoryService.updateCategory(updateCategoryDTO);
        return Result.success();
    }
}
