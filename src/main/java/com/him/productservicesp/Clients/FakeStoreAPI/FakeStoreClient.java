package com.him.productservicesp.Clients.FakeStoreAPI;
import com.him.productservicesp.Utilities_Converters.Convert;
import com.him.productservicesp.dtos.ProductDTO;
import com.him.productservicesp.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Arrays;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDTO getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response= restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                FakeStoreProductDTO.class, productId);
        FakeStoreProductDTO productDTO = response.getBody();
        return productDTO;
    }

    public FakeStoreProductDTO[] getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class);
        return response.getBody();
    }

    public FakeStoreProductDTO addNewProduct(ProductDTO productDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();
        //productDTO is added as input to be added, expected response in form of FakeStoreProductDTO.class
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity("https://fakestoreapi.com/products",
                productDTO, FakeStoreProductDTO.class);
        FakeStoreProductDTO productDTO1 = response.getBody();
        return productDTO1;
    }

    public FakeStoreProductDTO updateProduct(Long productId, Product product){
        /*
         productId: productId of the product to be updated, product contains the parameters that needs to be updated,
        some parameters which need not be updated remains null.
        */

        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();

        FakeStoreProductDTO fakeStoreProductDTO = Convert.ProductToFakeStoreProductDto(product);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.exchange("https://fakestoreapi.com/products/{id}",
                HttpMethod.PATCH, new HttpEntity<FakeStoreProductDTO>(fakeStoreProductDTO),
                FakeStoreProductDTO.class, productId);
        FakeStoreProductDTO productDTO = response.getBody();
        return productDTO;

    }

    public FakeStoreProductDTO replaceProduct(Long productId, Product product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDto = Convert.ProductToFakeStoreProductDto(product);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.exchange("https://fakestoreapi.com/products/{id}",
                HttpMethod.PUT, new HttpEntity<FakeStoreProductDTO>(fakeStoreProductDto),
                FakeStoreProductDTO.class, productId);
        FakeStoreProductDTO productDto = response.getBody();
        return productDto;
    }

    public FakeStoreProductDTO deleteProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.exchange("https://fakestoreapi.com/products/{id}",
                HttpMethod.DELETE, null, FakeStoreProductDTO.class, productId);
        FakeStoreProductDTO productDto = response.getBody();
        return productDto;
    }

    public List<String> getAllCategories(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> l = restTemplate.getForEntity("https://fakestoreapi.com/products/categories",
                String[].class);
        return Arrays.asList(l.getBody());
    }

    public List<FakeStoreProductDTO> getProductsInCategory(String categoryName){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products/category/{categoryName}",
                FakeStoreProductDTO[].class, categoryName);
        return Arrays.asList(response.getBody());
    }








}
