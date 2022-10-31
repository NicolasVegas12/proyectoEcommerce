package com.example.ecommerce.entity.dao.login;

import com.example.ecommerce.entity.dao.sales.Bag;
import com.example.ecommerce.entity.dao.sales.Sale;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Getter@Setter
    private Long id;

    @Column(name = "first_name")
    @Getter@Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter@Setter
    private String lastName;

    @Column(name = "email")
    @Getter@Setter
    private String email;

    @Column(name = "password")
    @Getter@Setter
    private String password;

    @Column(name = "active")
    @Getter@Setter
    private Boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bag")
    @Getter@Setter
    private Bag bag;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @Getter@Setter
    private List<Sale> sale;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"))
    @Getter@Setter
    private Collection<Role> roles;

    public User( String firstName, String lastName, String email, String password,Bag bag,  Collection<Role> roles) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.active = true;
        this.email = email;
        this.bag = bag;
        this.password = password;
        this.roles = roles;
    }

    public User(Long id, String firstName, String lastName, String email, String password, Boolean active, Collection<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User() {

    }

    public boolean hasRole(String roleName) {
        for (Role role : this.roles) {
            if (role.getName().equals(roleName)) {
                return true;
            }
        }

        return false;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}

