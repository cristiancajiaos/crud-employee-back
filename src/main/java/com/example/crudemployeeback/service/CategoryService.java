package com.example.crudemployeeback.service;

import com.example.crudemployeeback.record.CategoryDTO;
import java.util.List;

public interface CategoryService {

  CategoryDTO createCategory(CategoryDTO categoryDTO);

  List<CategoryDTO> getAllCategories();

}
