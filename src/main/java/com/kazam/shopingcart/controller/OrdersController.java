package com.kazam.shopingcart.controller;

import com.kazam.shopingcart.model.OrderDetails;
import com.kazam.shopingcart.model.Orders;
import com.kazam.shopingcart.service.OrdersService;
import com.kazam.shopingcart.service.ProductService;
import com.kazam.shopingcart.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersservice;

    @Autowired
    private ProductService productservice;

    @PostMapping("/addOrder")
    public ResponseEntity<Orders> saveOrders(@Valid @RequestBody Orders order){

        calculateTotals(order);
        ordersservice.saveOrders(order);
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
