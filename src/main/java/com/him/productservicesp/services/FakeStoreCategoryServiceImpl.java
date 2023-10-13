package com.him.productservicesp.services;

import com.him.productservicesp.Clients.FakeStoreAPI.FakeStoreClient;
import com.him.productservicesp.Clients.FakeStoreAPI.FakeStoreProductDTO;
import com.him.productservicesp.Utilities_Converters.Convert;
import com.him.productservicesp.models.Category;
import com.him.productservicesp.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService{

    private final FakeStoreClient fakeStoreClient;
    public FakeStoreCategoryServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }
    @Override
    public Optional<List<Category>> getAllCategories() {
        List<String> categories = fakeStoreClient.getAllCategories();
        if (categories == null) {
            return Optional.empty();
        }
        List<Category> answer = new ArrayList<>();
        for(String str : categories) {
            Category category = new Category();
            category.setName(str);
            answer.add(category);
        }
        return Optional.of(answer);
    }

    @Override
    public Optional<List<Product>> getProductsInCategory(String categoryName) {
        List<FakeStoreProductDTO> productDTOs = fakeStoreClient.getProductsInCategory(categoryName);
        if (productDTOs == null) {
            return Optional.empty();
        }
        List<Product> answer = new ArrayList<>();
        for(FakeStoreProductDTO productDto : productDTOs) {
            answer.add(Convert.FakeStoreProductDtoToProduct(productDto));
        }
        return Optional.of(answer);
    }
}
