package com.him.productservicesp.services;

import com.him.productservicesp.Utilities_Converters.Convert;
import com.him.productservicesp.dtos.ProductDTO;
import com.him.productservicesp.models.Product;
import com.him.productservicesp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Optional<Product[]> getAllProducts() {
        Optional<Product[]> result;
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            result = Optional.empty();
        } else {
            List<Product> ans = new ArrayList<>();
            for (Product product : products) {
                ans.add(product);
            }
            Product[] arr = new Product[ans.size()]; //just tried to return array instead of list
            for (int i = 0; i < arr.length; i++) {
                arr[i] = ans.get(i);
            }
            result = Optional.of(arr);
        }

        return result;
    }


    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if(product == null) {
            return Optional.empty();
        }
        return Optional.of(product);
    }

    @Override
    public Optional<Product> addNewProduct(ProductDTO productDto) {
        Product product = productRepository.save(Convert.ProductDtoToProduct(productDto));
        if(product == null) {
            return Optional.empty();
        }
        return Optional.of(product);
    }

    @Override
    public Optional<Product> updateProduct(Long productId, Product product) {
        Product prod = productRepository.findById(productId).orElse(null);
        if(prod == null) {
            return Optional.empty();
        }
        if(product.getTitle() != null) {
            prod.setTitle(product.getTitle());
        }
        if(product.getPrice() != 0) {
            prod.setPrice(product.getPrice());
        }
        if(product.getDescription() != null) {
            prod.setDescription(product.getDescription());
        }
        if(product.getImageUrl() != null) {
            prod.setImageUrl(product.getImageUrl());
        }
        productRepository.save(prod);
        return Optional.of(prod);
    }

    @Override
    public Optional<Product> replaceProduct(Long productId, Product product) {
        Product prod = productRepository.findById(productId).orElse(null);
        if(prod == null) {
            return Optional.empty();
        }
        prod.setTitle(product.getTitle());
        prod.setPrice(product.getPrice());
        prod.setDescription(product.getDescription());
        prod.setImageUrl(product.getImageUrl());
        productRepository.save(prod);
        return Optional.of(prod);
    }

    @Override
    public Optional<Product> deleteProduct(Long productId) {
        Product product = getSingleProduct(productId).orElse(null);
        if(product == null) {
            return Optional.empty();
        }
        productRepository.deleteById(productId);
        return Optional.of(product);
    }
}
