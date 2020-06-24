package com.kazam.shopingcart.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

@Entity
public class Orders {
    @Id
    @GeneratedValue
    @NonNull
    private int id;
    private double totalAmount;
    private String customerAddress;
    private String customerContact;
    private String customerName;
    private LocalDate orderDate;

    @OneToMany(targetEntity = OrderDetails.class, cascade= CascadeType.ALL)
    @JoinColumn(name="order_id",referencedColumnName = "id")
    private List<OrderDetails> orderdetails;

}
