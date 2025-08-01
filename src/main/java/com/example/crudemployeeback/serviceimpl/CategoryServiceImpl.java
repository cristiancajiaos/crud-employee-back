package com.example.crudemployeeback.serviceimpl;

import com.example.crudemployeeback.entity.Category;
import com.example.crudemployeeback.record.CategoryDTO;
import com.example.crudemployeeback.repository.CategoryRepository;
import com.example.crudemployeeback.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public CategoryServiceImpl(
      CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public CategoryDTO createCategory(CategoryDTO categoryDTO) {
    Category category = convertToEntity(categoryDTO);
    Category savedCategory = categoryRepository.save(category);
    return convertToDTO(savedCategory);
  }

  private Category convertToEntity(CategoryDTO categoryDTO) {
    Category category = new Category();
    category.setId(categoryDTO.id());
    category.setName(categoryDTO.name());
    return category;
  }

  private CategoryDTO convertToDTO(Category category) {
    return new CategoryDTO(category.getId(), category.getName());
  }
}
