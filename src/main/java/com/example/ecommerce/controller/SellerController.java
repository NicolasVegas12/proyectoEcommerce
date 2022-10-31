package com.example.ecommerce.controller;

import com.example.ecommerce.entity.dao.login.User;
import com.example.ecommerce.entity.dao.product.Product;
import com.example.ecommerce.entity.dao.sales.Sale;
import com.example.ecommerce.entity.dao.sales.Sale_Detail;
import com.example.ecommerce.repository.IDSaleRepository;
import com.example.ecommerce.repository.IProductRepository;
import com.example.ecommerce.repository.ISaleRepository;
import com.example.ecommerce.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SellerController {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ISaleRepository saleRepository;
    @Autowired
    private IDSaleRepository dSaleRepository;


    private String list_redirect="redirect:/admin";

    @GetMapping("/sales-register")
    public String registrar(Model model){
        List<Product> productList = productRepository.findAll() ;
        model.addAttribute("listProducts",productList);
        model.addAttribute("venta", new Sale());
        model.addAttribute("size", false);
        return "admin/register";
    }

    @PostMapping("/sales-register")
    public String save(@RequestParam(value = "idCantidad[]") List<String> idCantidad, @RequestParam(value = "idPrice[]") List<String> idPrice, @RequestParam(value = "idProducto[]") List<String> idProducto, @RequestParam(value = "fecha") String fecha, RedirectAttributes redirect) throws ParseException {
        String fechaString = "";
        fechaString = fecha;
        Sale venta = new Sale();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        venta.setUser(user);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formato.parse(fechaString);
        venta.setFecha(date);
        Double total = 0.0;
        for (int i = 0; i < idProducto.size(); i++) {
            Product pro = productRepository.findProductById(Long.parseLong(idProducto.get(i)));
            total += Integer.parseInt(idCantidad.get(i)) * pro.getPrice();
        }

        venta.setTotal(total);
        saleRepository.save(venta);
        for (int i = 0; i < idProducto.size(); i++) {
            Sale_Detail dVenta = new Sale_Detail();
            dVenta.setQuantity(Integer.parseInt(idCantidad.get(i)));
            Product pro = productRepository.findProductById(Long.parseLong(idProducto.get(i)));
            pro.restarStock(Integer.parseInt(idCantidad.get(i)));
            productRepository.save(pro);

            dVenta.setProduct(pro);
            dVenta.setSale(venta);

            dSaleRepository.save(dVenta);

        }

        redirect.addFlashAttribute("mensaje", "Venta Agregada correctamente")
                .addFlashAttribute("clase", "success");
        return list_redirect;
    }
    @GetMapping("/sales-edit")
    public String editar(@RequestParam(name = "id")  int id, Model model) {
        List<Product> productos = productRepository.findAll();
        model.addAttribute("productos", productos);

        Sale venta = saleRepository.findByIdSale(Long.parseLong(String.valueOf(id)))  ;
        Boolean size = venta.getDetails().size()>0;
        model.addAttribute("venta", venta);
        model.addAttribute("size", size);
        return "admin/register";
    }
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam(name = "id") int id, Model model) {
        saleRepository.delete(saleRepository.findByIdSale(Long.parseLong(String.valueOf(id))));
        return list_redirect;
    }
}
