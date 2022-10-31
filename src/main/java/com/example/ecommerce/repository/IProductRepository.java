package com.example.ecommerce.repository;

import com.example.ecommerce.entity.dao.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
    Product findProductByProductAndColor(String product,String color);
    Product findProductById(Long id);
}
