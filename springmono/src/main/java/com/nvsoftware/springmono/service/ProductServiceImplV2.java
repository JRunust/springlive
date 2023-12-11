package com.nvsoftware.springmono.service;

import com.nvsoftware.springmono.entity.ProductEntity;
import com.nvsoftware.springmono.model.Product;
import com.nvsoftware.springmono.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplV2 implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product save(Product product) {
        if(product.getId() == null){
            product.setId(UUID.randomUUID().toString());
        }
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(product, productEntity);
        productRepository.save(productEntity);
        return product;
    }

    @Override
    public Product getById(String id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        Product product = new Product();
        BeanUtils.copyProperties(productEntity, product);
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(productEntity -> {
            Product product = new Product();
            BeanUtils.copyProperties(productEntity,product);
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    public String deleteById(String id) {
        productRepository.deleteById(id);
        return "Delete product with id " + id;
    }
}
