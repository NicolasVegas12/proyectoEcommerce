package com.example.ecommerce.entity.dao.login;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    @Getter
    @Setter
    private Long id;

    @Column(name="name")
    @Getter@Setter
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role(String name) {
        this.name = name;
    }

    public Role() {

    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
