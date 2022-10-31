package com.example.ecommerce.entity.dao.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    @Getter@Setter
    private Long id;

    @Column(name = "product")
    @Getter@Setter
    private String product;

    @Column(name = "brand")
    @Getter@Setter
    private Double price;

    @Column(name = "color")
    @Getter@Setter
    private String color;

    @Column(name = "imagen")
    @Getter@Setter
    private String imagen;

    @Getter@Setter
    @Column(name="stock")
    private int stock;
    @ManyToOne()
    @JoinColumn(name = "id_subCategory")
    @Getter@Setter
    private SubCategory subCategory;

    @ManyToOne()
    @JoinColumn(name = "id_brand")
    @Getter@Setter
    private Brand brand;

    public Product( String product, Double price, String color, String imagen, SubCategory subCategory, Brand brand,int stock) {
        this.product = product;
        this.price = price;
        this.color = color;
        this.imagen = imagen;
        this.subCategory = subCategory;
        this.brand = brand;
        this.stock=stock;
    }

    public Product() {

    }
    public void restarStock(int stock){
        this.stock -= stock;
    }
}
