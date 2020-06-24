package com.kazam.shopingcart.service.impl;

import com.kazam.shopingcart.model.Orders;
import com.kazam.shopingcart.repository.OrdersRepository;
import com.kazam.shopingcart.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersrepository;

    public Orders saveOrders(Orders orders){

        return ordersrepository.save(orders);
    }

    public Orders getOrder(int id){
        return ordersrepository.findById(id).orElse(null);
    }

    public List<Orders> getOrdersByAddress(String customerAddress){
        return ordersrepository.findByCustomerAddress(customerAddress);
    }

    @Override
    public List<Orders> getOrdersByUserId(int customerId) {
        return null;
    }

    @Override
    public void deleteOrder(int orderId) {

    }

    @Override
    public Orders updateOrder(Orders orders) {
        return null;
    }
}

