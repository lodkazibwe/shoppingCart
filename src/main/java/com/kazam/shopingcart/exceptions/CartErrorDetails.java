package com.kazam.shopingcart.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartErrorDetails {
    private Date timeStamp;
    private String message;
    private String errorDetails;
}
