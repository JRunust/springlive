package com.nvsoftware.springmono.controller;

import com.nvsoftware.springmono.model.Product;
import com.nvsoftware.springmono.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }
}
