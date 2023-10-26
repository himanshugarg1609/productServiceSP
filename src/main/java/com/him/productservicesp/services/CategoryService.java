package com.him.productservicesp.services;

import com.him.productservicesp.models.Category;
import com.him.productservicesp.models.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<List<Category>> getAllCategories();

    Optional<Category> addNewCategory(Category category);

    Optional<List<Product>> getProductsInCategory(String categoryName);
}