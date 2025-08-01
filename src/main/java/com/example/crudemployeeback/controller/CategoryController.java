package com.example.crudemployeeback.controller;

import com.example.crudemployeeback.record.CategoryDTO;
import com.example.crudemployeeback.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping("")
  public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
    return categoryService.createCategory(categoryDTO);
  }
}
