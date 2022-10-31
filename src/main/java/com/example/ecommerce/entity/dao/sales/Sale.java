package com.example.ecommerce.entity.dao.sales;


import com.example.ecommerce.entity.dao.login.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @Column(name = "id_column")
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSale;

    @Getter@Setter
    @Column(name = "total")
    private Double total;

    @Getter@Setter
    @Column(name = "fecha")
    private Date fecha;


    @Getter@Setter
    @OneToMany(mappedBy = "sale",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Sale_Detail> details;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter@Setter
    private User user;
}
