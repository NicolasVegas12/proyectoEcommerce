package com.example.ecommerce.entity.dao.sales;

import com.example.ecommerce.entity.dao.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sale_detail")
public class Sale_Detail {
    @Id
    @Column(name = "id_sale_detail")
    @Getter@Setter
    private Long idSaleDetail;

    @Column(name = "quantity")
    @Getter @Setter
    private Double quantity;

    @OneToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_sale")
    @Getter@Setter
    private Sale sale;

}
