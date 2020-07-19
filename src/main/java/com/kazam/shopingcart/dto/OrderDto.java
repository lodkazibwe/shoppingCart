package com.kazam.shopingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @GeneratedValue
    private int id;
    private double totalAmount;
    @NotNull
    private String customerAddress;
    @NotNull
    private String customerContact;
    @NotNull
    private String customerName;
    private String userName;
    @NotNull
    private List<OrderDetailsDto> orderDetailsDto;

}
