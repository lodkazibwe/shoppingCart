package com.kazam.shopingcart.controller;

import com.kazam.shopingcart.model.OrderDetails;
import com.kazam.shopingcart.model.Orders;
import com.kazam.shopingcart.model.User;
import com.kazam.shopingcart.service.OrdersService;
import com.kazam.shopingcart.service.ProductService;
import com.kazam.shopingcart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersservice;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productservice;

    Logger logger= LoggerFactory.getLogger(OrdersController.class);

    @PostMapping("/addOrder")
    public ResponseEntity<Orders> saveOrders(@Valid @RequestBody Orders order, Principal principal){
        logger.info("adding order...");
        String user=principal.getName();
        User LoggedInUser=userService.getUserByUserName(user);
        order.setUser(LoggedInUser);

        calculateTotals(order);
        ordersservice.saveOrders(order);
        logger.info("order added");
        return new ResponseEntity<>(order, HttpStatus.OK);


    }

    @GetMapping("/getOrder/{id}")
    public Orders getOrder(@PathVariable int id){
        return ordersservice.getOrder(id);

    }

    @GetMapping("/getOrderByAddress/{customerAddress}")
    public List<Orders> getOrdersByAddress(@PathVariable String customerAddress){
        return ordersservice.getOrdersByAddress(customerAddress);
    }

    public void calculateTotals(Orders order){
        List<OrderDetails> newDetails =order.getOrderdetails();
        double total=0;
        for(OrderDetails detail : newDetails ){
            int pid=detail.getProduct().getId();
            int qtty=detail.getQuantity();
            double unitPrice=productservice.getProduct(pid).getUnitPrice();
            String name=productservice.getProduct(pid).getName();
            String cat=productservice.getProduct(pid).getCategory();
            int quantity=productservice.getProduct(pid).getQuantity();
            double amount=unitPrice*qtty;
            detail.setAmount(amount);
            detail.getProduct().setName(name);
            detail.getProduct().setCategory(cat);
            detail.getProduct().setQuantity(quantity);
            detail.getProduct().setUnitPrice(unitPrice);

            total+=amount;
        }
        LocalDate myDate = LocalDate.now();
        order.setOrderdetails(newDetails);
        order.setOrderDate(myDate);
        order.setTotalAmount(total);
    }

}

