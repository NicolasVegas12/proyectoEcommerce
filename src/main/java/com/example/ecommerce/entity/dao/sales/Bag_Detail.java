package com.example.ecommerce.entity.dao.sales;

import com.example.ecommerce.entity.dao.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bag_detail")
public class Bag_Detail {
    @Id
    @Column(name = "id_bag_detail")
    @Getter@Setter
    private Long idSaleDetail;

    @Column(name = "quantity")
    @Getter @Setter
    private Double quantity;

    @OneToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_bag")
    @Getter@Setter
    private Bag bag;

}
