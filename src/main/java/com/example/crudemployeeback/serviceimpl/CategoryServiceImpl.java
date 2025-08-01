package com.example.crudemployeeback.serviceimpl;

import com.example.crudemployeeback.entity.Category;
import com.example.crudemployeeback.record.CategoryDTO;
import com.example.crudemployeeback.repository.CategoryRepository;
import com.example.crudemployeeback.service.CategoryService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

  @Override
  public List<CategoryDTO> getAllCategories() {
    return categoryRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  @Override
  public Optional<CategoryDTO> getCategoryById(Long categoryId) {
    return categoryRepository.findById(categoryId).map(this::convertToDTO);
  }

  @Override
  public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
    Category foundCategory = categoryRepository.findById(categoryId).orElseThrow();
    foundCategory.setName(categoryDTO.name());
    Category updatedCategory = categoryRepository.save(foundCategory);
    return convertToDTO(updatedCategory);
  }

  @Override
  public CategoryDTO deleteCategory(Long categoryId) {
    Category foundCategory = categoryRepository.findById(categoryId).orElseThrow();
    categoryRepository.deleteById(categoryId);
    return convertToDTO(foundCategory);
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
