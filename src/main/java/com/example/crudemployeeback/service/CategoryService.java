package com.example.crudemployeeback.service;

import com.example.crudemployeeback.record.CategoryDTO;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

  CategoryDTO createCategory(CategoryDTO categoryDTO);

  List<CategoryDTO> getAllCategories();

  Optional<CategoryDTO> getCategoryById(Long categoryId);

  CategoryDTO deleteCategory(Long categoryId);

}
