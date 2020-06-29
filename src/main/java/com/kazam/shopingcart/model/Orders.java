package com.kazam.shopingcart.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Orders {
    @Id
    @GeneratedValue
    @NotNull
    private int id;
    private double totalAmount;
    @NotNull
    private String customerAddress;
    @NotNull
    private String customerContact;
    @NotNull
    private String customerName;
    private LocalDate orderDate;

    @OneToMany(targetEntity = OrderDetails.class, cascade= CascadeType.ALL)
    @JoinColumn(name="order_id",referencedColumnName = "id")
    @NotNull
    private List<OrderDetails> orderdetails;

    @ManyToOne
    private User user;

}
