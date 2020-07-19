package com.kazam.shopingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Column(unique=true)
    @NotEmpty
    @Size(min=2, message = "Product name at least two Characters")
    private String userName;

    @NotNull
    @Size(min=4)
    private String password;

    @Column(unique=true)
    @NotNull
    @Email
    private String userEmail;
    @NotEmpty
    private String address;
    @NotNull
    @Size(min=10, max=10)
    @Pattern(regexp="(^[0-9]{10})")
    private String contact;
   /* @OneToMany(targetEntity = Orders.class, cascade= CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private List<Orders> orders;*/

    @OneToMany(targetEntity = Role.class,cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn()
    private Set<Role> roles;

}
