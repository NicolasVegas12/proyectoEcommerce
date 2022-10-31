package com.example.ecommerce;

import com.example.ecommerce.entity.dao.login.Role;
import com.example.ecommerce.entity.dao.login.User;
import com.example.ecommerce.entity.dao.product.Brand;
import com.example.ecommerce.entity.dao.product.Category;
import com.example.ecommerce.entity.dao.product.SubCategory;
import com.example.ecommerce.entity.dto.UserRegistrationDto;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SetupData implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ISubCategoryRepository subCategoryRepository;
    @Autowired
    private IBrandRepository brandRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        createRoleIfNotFound("ROLE_USER");
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_VENDOR");
        createCategoryIfNotFound("General");
        createSubCategoryIfNotFound("General");
        createBrandIfNotFound("Nike","img/brand/nike.jpg");
        createUserIfNotFound(new UserRegistrationDto("admin","admin","admin@admin.com","123456"));
    }

    private Brand createBrandIfNotFound(String newBrand,String img) {
        Brand brand = brandRepository.findBrandByBrand(newBrand);
        if(brand==null){
            brand = new Brand(newBrand,img);
            brandRepository.save(brand);
        }
        return brand;
    }

    private SubCategory createSubCategoryIfNotFound(String newSubCategory) {
        SubCategory subCategory = subCategoryRepository.findSubCategoriesBySubCategory(newSubCategory);
        if(subCategory==null){
            subCategory = new SubCategory(newSubCategory,categoryRepository.findCategoriesByIdCategory(1L));
            subCategoryRepository.save(subCategory);
        }
        return subCategory;
    }

    private Category createCategoryIfNotFound(String newCategory) {
        Category category = categoryRepository.findCategoriesByCategory(newCategory);
        if(category==null){
            category= new Category(newCategory);
            categoryRepository.save(category);
        }
        return category;
    }

    @Transactional
    Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
    @Transactional
    User createUserIfNotFound(UserRegistrationDto registrationDto) {
        User user = userRepository.findByEmail(registrationDto.getUserName());
        if (user == null) {
            user = userService.save(registrationDto);
        }
        return user;
    }
}
