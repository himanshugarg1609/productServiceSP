package com.him.productservicesp.services;

import com.him.productservicesp.models.Category;
import com.him.productservicesp.models.Product;
import com.him.productservicesp.repositories.CategoryRepository;
import com.him.productservicesp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService{
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public SelfCategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public Optional<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(categories);
    }

    public Optional<Category> addNewCategory(Category category) {
        Category category1 = categoryRepository.save(category);
        if(category1 == null) {
            return Optional.empty();
        }
        return Optional.of(category1);
    }

    @Override
    public Optional<List<Product>> getProductsInCategory(String categoryName) {
        List<Product> products = productRepository.findByCategoryName(categoryName);
        if (((List<?>) products).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(products);
    }
}
