package com.nvsoftware.springmono.service;

import com.nvsoftware.springmono.error.ProductNotFoundException;
import com.nvsoftware.springmono.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    List<Product> products = new ArrayList<Product>();
    @Override
    public Product save(Product product) {
        if(product.getId() == null){
            product.setId(UUID.randomUUID().toString());
        }
        products.add(product);
        return product;
    }

    @Override
    public Product getById(String id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public String deleteById(String id) {
       Product element =  products.stream().filter(product -> product.getId().equals(id)).findFirst().get();
       products.remove(element);
       return "Deleted Product with id: " + id;
    }
}
