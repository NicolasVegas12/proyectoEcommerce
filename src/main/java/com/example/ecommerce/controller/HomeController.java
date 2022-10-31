package com.example.ecommerce.controller;

import com.example.ecommerce.entity.dao.product.Product;
import com.example.ecommerce.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    IProductRepository productRepository;


    @GetMapping("/")
    public String home(Model model){
        List<Product> list = productRepository.findAll();
        model.addAttribute("products",list);
        return "home/home";
    }

    @GetMapping("/view")
    public String view(Model model,@RequestParam(name="productId") Long productId){
        Product producto = productRepository.getReferenceById(productId);
        model.addAttribute("product",producto);
        return "viewProduct/Product";
    }
}
