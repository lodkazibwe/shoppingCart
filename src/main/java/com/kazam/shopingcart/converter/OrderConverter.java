package com.kazam.shopingcart.converter;

import com.kazam.shopingcart.dto.OrderDto;
import com.kazam.shopingcart.model.OrderDetails;
import com.kazam.shopingcart.model.Orders;
import com.kazam.shopingcart.model.User;
import com.kazam.shopingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {
    @Autowired OrderDetailsConverter orderDetailsConverter;
    @Autowired UserService userService;
    public OrderDto entityToDto(Orders order){
        OrderDto orderDto=new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setCustomerAddress(order.getCustomerAddress());
        orderDto.setCustomerContact(order.getCustomerContact());
        orderDto.setCustomerName(order.getCustomerName());
        orderDto.setUserName(order.getUser().getUserName());
        orderDto.setOrderDetailsDto(
                orderDetailsConverter.entityToDto(order.getOrderDetails())
        );
        return orderDto;
    }

    public Orders dtoToEntity(OrderDto orderDto){
        Orders order= new Orders();
        User user=userService.getUserByUserName(orderDto.getUserName());
        List<OrderDetails> orderDetails=orderDetailsConverter.dtoToEntity(orderDto.getOrderDetailsDto());
        order.setId(orderDto.getId());
        order.setCustomerAddress(orderDto.getCustomerAddress());
        order.setCustomerContact(orderDto.getCustomerContact());
        order.setCustomerName(orderDto.getCustomerName());
        order.setOrderDate(LocalDate.now());
        order.setUser(user);
        order.setOrderDetails(orderDetails);
        double totalAmount=0;
        for(OrderDetails detail : orderDetails ){
            double amount=detail.getAmount();
            totalAmount+=amount;
        }
        order.setTotalAmount(totalAmount);
        return order;
    }

    public List<OrderDto> entityToDto(List<Orders> orders){
        return orders.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
