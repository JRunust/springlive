package com.nvsoftware.springmono.service;

import com.nvsoftware.springmono.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product getById(String id);
    List<Product> getAll();
    String deleteById(String id);
}
