package com.him.productservicesp.Utilities_Converters;

import com.him.productservicesp.Clients.FakeStoreAPI.FakeStoreProductDTO;
import com.him.productservicesp.dtos.ProductDTO;
import com.him.productservicesp.models.Category;
import com.him.productservicesp.models.Product;

public class Convert {
    public static Product FakeStoreProductDtoToProduct(FakeStoreProductDTO productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        return product;
    }

    public static FakeStoreProductDTO ProductToFakeStoreProductDto(Product product) {
        FakeStoreProductDTO productDto = new FakeStoreProductDTO();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        return productDto;
    }

    public static Product ProductDtoToProduct(ProductDTO productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        return product;
    }
}
