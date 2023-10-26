package com.him.productservicesp.repositories;

import com.him.productservicesp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);
    List<Product> findByCategoryName(String categoryName);
}
