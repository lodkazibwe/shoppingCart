package com.kazam.shopingcart.service.impl;

import com.kazam.shopingcart.model.Product;
import com.kazam.shopingcart.repository.ProductRepository;
import com.kazam.shopingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productrepository;

public Product saveProduct(Product product){
     return productrepository.save(product);

}

    public Product getProduct(int id){
        return productrepository.findById(id).orElse(null);
    }
    public List<Product> getAllProducts(){
        return productrepository.findAll();
    }

    public Product updateProduct(Product product){
    return productrepository.save(product);
    }

    public void deleteProduct(int id){
         productrepository.deleteById(id);
    }

    @Override
    public List<Product> saveProducts(List<Product> products) {
        return null;
    }

    @Override
    public List<Product> getProductByCat(String category) {
        return null;
    }
}
