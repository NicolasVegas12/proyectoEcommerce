package com.example.ecommerce.entity.dao.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brand")
public class Brand  {

    @Id
    @Getter@Setter
    @Column(name = "id_brand")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBrand;

    @Column(name = "brand")
    @Getter@Setter
    private String brand;

    @Getter@Setter
    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Product> products;

    @Getter@Setter
    @Column(name = "img")
    private String img;

    public Brand(String newBrand, String img) {
        this.brand = newBrand;
        this.img = img;
    }

    public Brand() {

    }
}
