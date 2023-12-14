package net.nvsoftware.ProductService.service;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import net.nvsoftware.ProductService.entity.ProductEntity;
import net.nvsoftware.ProductService.model.ProductRequest;
import net.nvsoftware.ProductService.model.ProductResponse;
import net.nvsoftware.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Start product service: adding product");
        ProductEntity productEntity = ProductEntity.builder().
                name(productRequest.getName()).
                price(productRequest.getPrice()).
                quantity(productRequest.getQuantity()).build();
        productRepository.save(productEntity);
        log.info("End product service: adding product");
        return productEntity.getId();
    }

    @Override
    public ProductResponse findById(long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductService get by id not found with id: " + id));
        ProductResponse productResponse = ProductResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .quantity(productEntity.getQuantity())
                .price(productEntity.getPrice())
                .build();
        return productResponse;
    }

    @Override
    public void reduceQuantity(long id, long quantity) {
        log.info("Start: ProductService reduce quantity with id: " + id + "and quantity: " + quantity);
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(()-> new RuntimeException("reduceQuantity: Product not found with id: " + id));
        if(productEntity.getQuantity() < quantity){
            throw new RuntimeException("reduceQuantity: Product not enough with id: " + id);
        }
        productEntity.setQuantity(productEntity.getQuantity() - quantity);
        productRepository.save(productEntity);
        log.info("End: ProductService reduce quantity with id: " + id + "and quantity: " + quantity);
    }
}
