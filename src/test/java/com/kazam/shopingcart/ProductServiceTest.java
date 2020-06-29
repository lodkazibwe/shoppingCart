package com.kazam.shopingcart;

import com.kazam.shopingcart.model.Product;
import com.kazam.shopingcart.repository.ProductRepository;
import com.kazam.shopingcart.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getAllProducts(){
        //When(productRepository.findAll()).
        when(productRepository.findAll()).thenReturn(Stream
                .of(new Product( 6,"USB","Electronics",5000.0,40),
                    new Product(7,"HDD","Electronics",50000.0,12))
                .collect(Collectors.toList()));
        assertEquals(2,productService.getAllProducts().size());
    }
    @Test
    public void saveProductTest(){
        Product product=new Product(6,"USB","Electronics",5000.0,40);
        when(productRepository.save(product)).thenReturn(product);
        assertEquals(product, productRepository.save(product));

    }
    @Test
    public void deleteProductTest(){
        Product product=new Product(6,"USB","Electronics",5000.0,40);
        productRepository.deleteById(product.getId());
        verify(productRepository, times(1)).deleteById(product.getId());
    }

    /*@Test
    public void getProductTest(){
        Product product=new Product(6,"USB","Electronics",5000.0,40);
        when(productRepository.findById(product.getId())).thenReturn(product);
        assertEquals(product, productRepository.findById(product.getId()));
    }*/
}

