package com.example.crudemployeeback.controller;

import com.example.crudemployeeback.record.CategoryDTO;
import com.example.crudemployeeback.service.CategoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

  @GetMapping("")
  public List<CategoryDTO> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @GetMapping("{id}")
  public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long categoryId) {
    Optional<CategoryDTO> category = categoryService.getCategoryById(categoryId);
    return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("{id}")
  public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long categoryId, @RequestBody CategoryDTO categoryDTO) {
    try {
      CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO, categoryId);
      return ResponseEntity.ok(updatedCategory);
    } catch (Exception e) {
      System.err.println("Error: " + e);
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable("id") Long categoryId) {
    try {
      CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
      return ResponseEntity.ok(deletedCategory);
    } catch (Exception e) {
      System.err.println("Error: " + e);
      return ResponseEntity.notFound().build();
    }
  }
}
