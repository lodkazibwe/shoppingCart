package com.kazam.shopingcart.converter;

import com.kazam.shopingcart.dto.OrderDetailsDto;
import com.kazam.shopingcart.model.OrderDetails;
import com.kazam.shopingcart.model.Product;
import com.kazam.shopingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDetailsConverter {
    @Autowired private ProductService productService;

    public OrderDetailsDto entityToDto(OrderDetails orderDetails){
       OrderDetailsDto orderDetailsDto= new OrderDetailsDto();
        orderDetailsDto.setId(orderDetails.getId());
        orderDetailsDto.setProductId(orderDetails.getProduct().getId());
        orderDetailsDto.setQuantity(orderDetails.getQuantity());
        orderDetailsDto.setAmount(orderDetails.getAmount());
        return orderDetailsDto;
    }

    public OrderDetails dtoToEntity(OrderDetailsDto orderDetailsDto){
        OrderDetails orderDetails=new OrderDetails();
        int productId=orderDetailsDto.getProductId();
        int quantity=orderDetailsDto.getQuantity();
        Product product=productService.getProduct(productId);
        orderDetails.setId(orderDetailsDto.getId());
        orderDetails.setProduct(product);
        orderDetails.setQuantity(quantity);
        orderDetails.setAmount(product.getUnitPrice()*quantity);
        return orderDetails;

    }
    public List<OrderDetailsDto> entityToDto(List<OrderDetails> orderDetails){
        //return orderDetails.stream().map(x->entityToDto(x)).collect(Collectors.toList());
        //replace lambda with method reference*
        return orderDetails.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<OrderDetails> dtoToEntity(List<OrderDetailsDto> orderDetailsDto){
        return orderDetailsDto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
