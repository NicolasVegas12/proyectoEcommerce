package com.example.ecommerce.repository;

import com.example.ecommerce.entity.dao.sales.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleRepository extends JpaRepository<Sale,Long> {
}
