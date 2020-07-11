package com.kazam.shopingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class OrderDetails {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @NotNull
    private Product product;
    @NotNull
    @Digits(integer=10, fraction=0)
    private int quantity;
    @NotNull
    private double amount;

}

