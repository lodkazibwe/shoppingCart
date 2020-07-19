package com.kazam.shopingcart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderDetailsDto {
    @GeneratedValue
    private int id;
    @NotNull
    private int productId;
    @NotNull
    @Digits(integer=10, fraction=0)
    private int quantity;
    private double amount;
}
