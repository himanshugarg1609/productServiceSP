package com.him.productservicesp.services;

import com.him.productservicesp.dtos.ProductDTO;
import com.him.productservicesp.models.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product[]> getAllProducts();
    Optional<Product> getSingleProduct(Long productId);
    Optional<Product>addNewProduct(ProductDTO productDTO);
    Optional<Product> updateProduct(Long productId, Product product);
    Optional<Product> replaceProduct(Long productId, Product product);
    Optional<Product> deleteProduct(Long productId);
}
