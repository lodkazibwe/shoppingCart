package com.kazam.shopingcart.repository;

import com.kazam.shopingcart.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
        List<Orders> findByCustomerAddress(String customerAddress);
}
