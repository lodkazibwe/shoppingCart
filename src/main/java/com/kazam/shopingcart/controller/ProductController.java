package com.kazam.shopingcart.controller;

import com.kazam.shopingcart.model.Product;
import com.kazam.shopingcart.service.ProductService;
import com.kazam.shopingcart.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

@Autowired
ProductService productservice;

@PostMapping("/addProduct")
public Product saveProduct ( @RequestBody Product product){
    return productservice.saveProduct(product);

}

@GetMapping("/getProduct/{id}")
public Product getProduct(@PathVariable int id){
    return productservice.getProduct(id);
}

@GetMapping("/")
    public List<Product> getAllProducts(){
    return productservice.getAllProducts();
    }

@PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product){
       return productservice.updateProduct(product);

    }

@DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable int id){
    productservice.deleteProduct(id);
    }

}



