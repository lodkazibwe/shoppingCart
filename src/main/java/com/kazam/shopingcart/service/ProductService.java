package com.kazam.shopingcart.service;

import com.kazam.shopingcart.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> saveProducts(List<Product> products);
    Product getProduct(int id);
    List<Product> getAllProducts();
    List<Product> getProductByCat(String category);
    Product updateProduct(Product product);
    void deleteProduct(int id);

}
