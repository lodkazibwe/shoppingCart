package com.kazam.shopingcart;

import com.kazam.shopingcart.model.OrderDetails;
import com.kazam.shopingcart.model.Orders;
import com.kazam.shopingcart.model.Product;
import com.kazam.shopingcart.model.User;
import com.kazam.shopingcart.repository.OrdersRepository;
import com.kazam.shopingcart.service.OrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrdersServiceTest {
    @Autowired
    private OrdersService ordersService;
    @MockBean
    private OrdersRepository ordersRepository;

    @Test
    public void saveOrders(){
        LocalDate myDate = LocalDate.now();
        List<OrderDetails> orderDetailsList= new ArrayList<>();
        User user=new User();
        Product product1=new Product();
        Product product2=new Product();
        orderDetailsList.add(new OrderDetails(2,product1,10,1000));
        orderDetailsList.add(new OrderDetails(4,product2,20,1500));

        Orders order =new Orders(1,11.1,"kampala","077733",
                "kato",myDate,user,orderDetailsList);
        when(ordersRepository.save(order)).thenReturn(order);
        assertEquals(order, ordersService.saveOrders(order));
    }
    @Test
    public void getOrdersByAddress(){
        when(ordersRepository.findByCustomerAddress("mukono")).thenReturn(Stream
                .of(new Orders(),new Orders()).collect(Collectors.toList()));
        assertEquals(2,ordersService.getOrdersByAddress("mukono").size());
    }

}
