package com.example.ecommerce.repository;

import com.example.ecommerce.entity.dao.login.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
