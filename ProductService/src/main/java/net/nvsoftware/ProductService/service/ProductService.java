package net.nvsoftware.ProductService.service;

import net.nvsoftware.ProductService.model.ProductRequest;
import net.nvsoftware.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);
    ProductResponse findById(long id);

    void reduceQuantity(long id,long quantity);
}
