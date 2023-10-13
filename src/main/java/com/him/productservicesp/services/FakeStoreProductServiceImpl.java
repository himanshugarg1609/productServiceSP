package com.him.productservicesp.services;

import com.him.productservicesp.Clients.FakeStoreAPI.FakeStoreClient;
import com.him.productservicesp.Clients.FakeStoreAPI.FakeStoreProductDTO;
import com.him.productservicesp.Utilities_Converters.Convert;
import com.him.productservicesp.dtos.ProductDTO;
import com.him.productservicesp.models.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService {
    private final FakeStoreClient fakeStoreClient;
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient){
        this.fakeStoreClient=fakeStoreClient;
    }

    @Override
    public Optional<Product[]> getAllProducts() {
        FakeStoreProductDTO[] fakeStoreProductDTOs = fakeStoreClient.getAllProducts();

        if(fakeStoreProductDTOs==null) return Optional.empty();

        List<Product> ans = new ArrayList<>();
        for (FakeStoreProductDTO productDTO : fakeStoreProductDTOs) {
            ans.add(Convert.FakeStoreProductDtoToProduct(productDTO));
        }

        Product[] arr = new Product[ans.size()]; //just tried to return array instead of list
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ans.get(i);
        }
        return Optional.of(arr);
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreClient.getSingleProduct(productId);

        if(fakeStoreProductDTO==null) return Optional.empty();
        return Optional.of(Convert.FakeStoreProductDtoToProduct(fakeStoreProductDTO));
    }

    @Override
    public Optional<Product> addNewProduct(ProductDTO product) {
        FakeStoreProductDTO productDTO1 = fakeStoreClient.addNewProduct(product);

        if(productDTO1==null) return Optional.empty();
        return Optional.of(Convert.FakeStoreProductDtoToProduct(productDTO1));
    }

    @Override
    public Optional<Product>updateProduct(Long productId, Product product) {
        FakeStoreProductDTO productDTO = fakeStoreClient.updateProduct(productId, product);

        if(productDTO==null) return Optional.empty();
        return Optional.of(Convert.FakeStoreProductDtoToProduct(productDTO));
    }

    @Override
    public Optional<Product> replaceProduct(Long productId, Product product) {
        FakeStoreProductDTO productDTO = fakeStoreClient.replaceProduct(productId, product);

        if(productDTO==null) return Optional.empty();
        return Optional.of(Convert.FakeStoreProductDtoToProduct(productDTO));
    }

    @Override
    public Optional<Product> deleteProduct(Long productId) {
        FakeStoreProductDTO productDTO = fakeStoreClient.deleteProduct(productId);

        if(productDTO==null) return Optional.empty();
        return Optional.of(Convert.FakeStoreProductDtoToProduct(productDTO));
    }
}
