package com.kazam.shopingcart.service.impl;

import com.kazam.shopingcart.model.OrderDetails;
import com.kazam.shopingcart.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersDetailsServiceImpl {
    @Autowired
    private OrderDetailsRepository orderdetailsrepository;

    public OrderDetails addOrderDetail(OrderDetails orderdetails, int orderId){
        orderdetailsrepository.save(orderdetails);
        int newOrderId=orderdetails.getId();
        return updateAmount(newOrderId);
         //newOrder;

    }



    public OrderDetails updateAmount(int id){
        OrderDetails orderdetails=orderdetailsrepository.findById(id).orElse(null);
        double uprice =orderdetails.getProduct().getUnitPrice();
        int qtty=orderdetails.getQuantity();
        orderdetails.setAmount(id);
        return orderdetailsrepository.save(orderdetails);

    }

}
