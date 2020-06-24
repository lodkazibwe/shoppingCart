package com.kazam.shopingcart.service;

import com.kazam.shopingcart.model.Orders;

import java.util.List;

public interface OrdersService {

    public Orders saveOrders(Orders orders);
    public Orders getOrder(int id);
    public List<Orders> getOrdersByAddress(String customerAddress);
    public List<Orders> getOrdersByUserId(int customerId);
    public void deleteOrder(int orderId);
    public Orders updateOrder(Orders orders);


}
