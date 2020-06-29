package com.kazam.shopingcart.controller;

import com.kazam.shopingcart.model.Product;
import com.kazam.shopingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

@Autowired
ProductService productservice;

@PostMapping("/addProduct")
public ResponseEntity<Product> saveProduct (@Valid @RequestBody Product product, BindingResult bindingResult){
    return new ResponseEntity<>(productservice.saveProduct(product), HttpStatus.OK);


}

@GetMapping("/getProduct/{id}")
public ResponseEntity<Product> getProduct(@PathVariable int id){
    Product product=productservice.getProduct(id);
    return new ResponseEntity<>(product, HttpStatus.OK);
}

@GetMapping("/")
    public  List<Product> getAllProducts(){
    return productservice.getAllProducts();
    }

@PutMapping("/updateProduct")
    public ResponseEntity <Product> updateProduct(@Valid @RequestBody Product product){
      productservice.updateProduct(product);
    return new ResponseEntity<>(product, HttpStatus.OK);
    }

@DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable int id){
    productservice.deleteProduct(id);
    }

}



