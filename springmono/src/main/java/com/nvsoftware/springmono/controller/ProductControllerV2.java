package com.nvsoftware.springmono.controller;

import com.nvsoftware.springmono.model.Product;
import com.nvsoftware.springmono.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/product")
public class ProductControllerV2 {
    @Autowired
    ProductService productServiceImplV2;
    @PostMapping()
    public Product save(@RequestBody Product product) {
        return productServiceImplV2.save(product);
    }
    @GetMapping
    public List<Product> getProducts() {
        return productServiceImplV2.getAll();
    }
    @GetMapping("/{id}")
    public Product getProductByProductId(@PathVariable String id) {
        return productServiceImplV2.getById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable String id) {
        return productServiceImplV2.deleteById(id);
    }
}
