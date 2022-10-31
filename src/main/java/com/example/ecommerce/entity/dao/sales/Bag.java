package com.example.ecommerce.entity.dao.sales;

import com.example.ecommerce.entity.dao.login.User;
import com.example.ecommerce.entity.dao.product.Product;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "bag")
public class Bag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter@Setter
    @Column(name = "id_bag")
    private Long idBag;

    @Getter@Setter
    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "bag",cascade = CascadeType.ALL,orphanRemoval = true)
    @Getter@Setter
    private List<Bag_Detail> details;
}
