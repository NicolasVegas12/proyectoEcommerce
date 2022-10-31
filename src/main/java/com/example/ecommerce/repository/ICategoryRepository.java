package com.example.ecommerce.repository;

import com.example.ecommerce.entity.dao.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
    Category findCategoriesByCategory(String category);
    Category findCategoriesByIdCategory(Long idCategory);
}
