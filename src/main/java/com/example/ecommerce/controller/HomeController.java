package com.example.ecommerce.controller;

import com.example.ecommerce.entity.dao.login.User;
import com.example.ecommerce.entity.dao.product.Brand;
import com.example.ecommerce.entity.dao.product.Product;
import com.example.ecommerce.entity.dao.sales.Bag;
import com.example.ecommerce.repository.IBagRepository;
import com.example.ecommerce.repository.IBrandRepository;
import com.example.ecommerce.repository.IProductRepository;
import com.example.ecommerce.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    IProductRepository productRepository;

    @Autowired
    IUserRepository userRepository;
    @Autowired
    IBrandRepository brandRepository;

    @Autowired
    IBagRepository bagRepository;


    @GetMapping("/")
    public String home(Model model){
        List<Product> list = productRepository.findAll();
        List<Brand> brands = brandRepository.findAll();
        model.addAttribute("products",list);
        model.addAttribute("brands",brands);
        return "home/home";
    }

    @GetMapping("/view")
    public String view(Model model,@RequestParam(name="productId") Long productId){
        Product producto = productRepository.findProductById(productId);

        model.addAttribute("product",producto);
        return "viewProduct/Product";
    }

    @GetMapping("/bag")
    public String bag(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        Bag bag = bagRepository.findBagByIdBag(user.getBag().getIdBag());
        model.addAttribute("bag",bag);
        return "bag/Bag";

    }
}
