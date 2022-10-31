package com.example.ecommerce.controller;

import com.example.ecommerce.entity.dao.sales.Sale;
import com.example.ecommerce.repository.ISaleRepository;
import com.example.ecommerce.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ISaleRepository saleRepository;

    @GetMapping("/admin")
    public String admin(Model model){
        List<Sale> list = saleRepository.findAll() ;
        model.addAttribute("sales",list);
        return "admin/index";
    }
}
