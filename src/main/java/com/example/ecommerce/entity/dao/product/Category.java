package com.example.ecommerce.entity.dao.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter@Setter
    @Column(name = "id_category")
    private Long idCategory;

    @Getter@Setter
    @Column(name = "category")
    private String category;

    @Getter@Setter
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SubCategory> subCategories;


    public Category(String newCategory) {
        this.category = newCategory;
    }

    public Category() {

    }
}
