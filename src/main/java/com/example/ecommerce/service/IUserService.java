package com.example.ecommerce.service;

import com.example.ecommerce.entity.dao.login.User;
import com.example.ecommerce.entity.dto.UserRegistrationDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    User findByUsername(String username);

}
