package com.kazam.shopingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class OrderDetails {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private com.kazam.shopingcart.model.Product product;

    private int quantity;
    private double amount;

}

