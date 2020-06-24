package com.kazam.shopingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private String password;
    private String userEmail;
    private String address;
    private String contact;
    @OneToMany(targetEntity = Orders.class, cascade= CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private List<Orders> orders;

    @OneToMany(targetEntity = Role.class,cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn()
    private Set<Role> roles;


}
