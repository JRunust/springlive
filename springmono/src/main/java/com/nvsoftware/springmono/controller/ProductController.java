package com.nvsoftware.springmono.controller;

import com.nvsoftware.springmono.model.Product;
import com.nvsoftware.springmono.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    ProductService productServiceImpl;
    @PostMapping()
    public Product save(@RequestBody Product product) {
        return productServiceImpl.save(product);
    }
    @GetMapping
    public List<Product> getProducts() {
        return productServiceImpl.getAll();
    }
    @GetMapping("/{id}")
    public Product getProductByProductId(@PathVariable String id) {
        return productServiceImpl.getById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable String id) {
        return productServiceImpl.deleteById(id);
    }
}
