package com.example.ecommerce.repository;

import com.example.ecommerce.entity.dao.sales.Sale_Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDSaleRepository extends JpaRepository<Sale_Detail,Long> {
}
