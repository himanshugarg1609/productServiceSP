package com.him.productservicesp.controllers;

import com.him.productservicesp.Exceptions.NotFoundException;
import com.him.productservicesp.models.Category;
import com.him.productservicesp.models.Product;
import com.him.productservicesp.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping()
    public List<Category> getAllCategories() throws NotFoundException {
        if (categoryService.getAllCategories().isEmpty()) {
            throw new NotFoundException("No Categories Found");
        }
        return categoryService.getAllCategories().get();
    }

    @GetMapping("/{categoryName}")
    public List<Product> getProductsInCategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {
        if (categoryService.getProductsInCategory(categoryName).isEmpty()) {
            throw new NotFoundException("No products found in the Category: " + categoryName);
        }
        return categoryService.getProductsInCategory(categoryName).get();
    }
}
