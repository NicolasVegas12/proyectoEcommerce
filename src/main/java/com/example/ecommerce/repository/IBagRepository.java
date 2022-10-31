package com.example.ecommerce.repository;

import com.example.ecommerce.entity.dao.sales.Bag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBagRepository extends JpaRepository<Bag,Long> {
    Bag findBagByIdBag(Long idBag);
}
