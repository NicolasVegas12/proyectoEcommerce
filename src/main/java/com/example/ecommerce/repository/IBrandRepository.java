package com.example.ecommerce.repository;

import com.example.ecommerce.entity.dao.product.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository extends JpaRepository<Brand,Long> {
    Brand findBrandByBrand(String brand);
}
