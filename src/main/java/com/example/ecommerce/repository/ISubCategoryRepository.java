package com.example.ecommerce.repository;

import com.example.ecommerce.entity.dao.product.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubCategoryRepository extends JpaRepository<SubCategory,Long> {
    SubCategory findSubCategoriesBySubCategory(String subCategory);
}
