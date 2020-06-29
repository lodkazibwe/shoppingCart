package com.kazam.shopingcart.service;

import com.kazam.shopingcart.model.Orders;

import java.util.List;

public interface OrdersService {

    Orders saveOrders(Orders orders);
    Orders getOrder(int id);
    List<Orders> getOrdersByAddress(String customerAddress);
    List<Orders> getOrdersByUserId(int customerId);
    void deleteOrder(int orderId);
    Orders updateOrder(Orders orders);


}
