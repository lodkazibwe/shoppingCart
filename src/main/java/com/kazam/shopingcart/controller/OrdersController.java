package com.kazam.shopingcart.controller;

import com.kazam.shopingcart.converter.OrderConverter;
import com.kazam.shopingcart.dto.OrderDto;
import com.kazam.shopingcart.dto.PaymentDto;
import com.kazam.shopingcart.model.Orders;
import com.kazam.shopingcart.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrdersController {

    @Autowired  private OrdersService ordersservice;
    @Autowired  RestTemplate restTemplate;
    @Autowired OrderConverter orderConverter;


    Logger logger= LoggerFactory.getLogger(OrdersController.class);

    @PostMapping("/addOrder")
    public ResponseEntity<String> saveOrders(@Valid @RequestBody OrderDto orderDto, Principal principal){
        final String uri = "http://localhost:8083/api/addPayment";
        logger.info("adding order...");
        String user=principal.getName();
        orderDto.setUserName(user);
        Orders order= orderConverter.dtoToEntity(orderDto);

        PaymentDto payment=new PaymentDto();
        payment.setAmount(order.getTotalAmount());
        payment.setCustomerName(order.getUser().getUserName());

        ordersservice.saveOrders(order);
        logger.info("order added");
        /*HttpHeaders headers =new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PaymentDto> entity=new HttpEntity<PaymentDto>(payment, headers);
        return restTemplate.exchange(uri, HttpMethod.POST,entity, PaymentDto.class);*/
        //String paid=restTemplate.postForObject(uri,payment,String.class);
        return new ResponseEntity<>(
                restTemplate.postForObject(uri,payment,String.class),
                HttpStatus.OK);

    }

    @GetMapping("/getOrder/{id}")
    public OrderDto getOrder(@PathVariable int id){
        return orderConverter.entityToDto(ordersservice.getOrder(id));

    }

    @GetMapping("/getOrderByAddress/{customerAddress}")
    public List<OrderDto> getOrdersByAddress(@PathVariable String customerAddress){
        return orderConverter.entityToDto(ordersservice.getOrdersByAddress(customerAddress));
    }


}

