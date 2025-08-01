package com.example.crudemployeeback.repository;

import com.example.crudemployeeback.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
