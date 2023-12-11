package com.nvsoftware.springmono.controller;

import com.nvsoftware.springmono.error.ProductNotFoundException;
import com.nvsoftware.springmono.model.Product;
import com.nvsoftware.springmono.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping()
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }
    @GetMapping
    public List<Product> getProducts() {
        return productService.getAll();
    }
    @GetMapping("/{id}")
    public Product getProductByProductId(@PathVariable String id) {
        return productService.getById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable String id) {
        return productService.deleteById(id);
    }
}
