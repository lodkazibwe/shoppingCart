package com.kazam.shopingcart.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    private int id;
    private double totalAmount;
    @NotNull
    private String customerAddress;
    @NotNull
    private String customerContact;
    @NotNull
    private String customerName;
    private LocalDate orderDate;
    //private String date;

    @ManyToOne
    private User user;

    @OneToMany(targetEntity = OrderDetails.class, cascade= CascadeType.ALL)
    @JoinColumn(name="order_id",referencedColumnName = "id")
    private List<OrderDetails> orderdetails;


}
