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
    @ManyToOne()
    @JoinColumn(name = "id_subCategory")
    @Getter@Setter
    private SubCategory subCategory;

    @ManyToOne()
    @JoinColumn(name = "id_brand")
    @Getter@Setter
    private Brand brand;
}
