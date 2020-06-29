package com.kazam.shopingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;


//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
//jpa
@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Size(min=2, message = "Product name at least two Characters")
    private String name;
    @NotEmpty
    private String category;
    @NotBlank
    @Digits(integer = 10, fraction = 2)
    private double unitPrice;
    @NotBlank
    @Digits(integer=10, fraction=0)
    private int quantity;

}


