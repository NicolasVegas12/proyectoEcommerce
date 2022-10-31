package com.example.ecommerce.entity.dao.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "subCategory")
public class SubCategory {
    @Id
    @Getter@Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subCategory")
    private Long idSubCategorie;

    @Getter@Setter
    @Column(name = "subCategory")
    private String subCategory;

    @Getter@Setter
    @ManyToOne()
    @JoinColumn(name = "id_category")
    private Category category;

    @OneToMany(mappedBy = "subCategory",cascade = CascadeType.ALL,orphanRemoval = true)
    @Getter@Setter
    private List<Product> products;

    public SubCategory(String subcategory, Category category) {
        this.subCategory=subcategory;
        this.category=category;
    }

    public SubCategory() {

    }
}
