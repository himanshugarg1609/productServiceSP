package com.him.productservicesp.controllers;

import com.him.productservicesp.Exceptions.NotFoundException;
import com.him.productservicesp.Utilities_Converters.Convert;
import com.him.productservicesp.dtos.ProductDTO;
import com.him.productservicesp.models.Product;
import com.him.productservicesp.repositories.ProductRepository;
import com.him.productservicesp.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    public final ProductService productService;
    private ProductRepository productRepository;

    public ProductController( ProductService productService, ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productService = productService;
    }


    @GetMapping()
    public Product[] getAllProducts() throws NotFoundException {
        if(productService.getAllProducts().isEmpty()){
            throw new NotFoundException("Not a Single Product Found");
        }
        return productService.getAllProducts().get();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token", "No_Access_for_you");

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if(productOptional.isEmpty()){
            throw new NotFoundException("No Product Found with Id: "+productId);
        }

        ResponseEntity<Product> response  = new ResponseEntity<>(
                productOptional.get(),
                headers,
                HttpStatus.OK //also setting a http_status ourselves
        );

        return response;
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDTO productDTO) throws NotFoundException{
        Optional<Product> productOptional = productService.addNewProduct(productDTO);
        if(productOptional==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //directly returned a BAD_REQUEST if no productDTO in body provided
        }

        ResponseEntity<Product> response = new ResponseEntity<>(
                productOptional.get(),
                HttpStatus.OK
        );

        //DB SAVE METHOD:
        Product newProduct = new Product();
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setImageUrl(productDTO.getImage());
        newProduct.setTitle(productDTO.getTitle());
        newProduct.setPrice(productDTO.getPrice());

        newProduct = productRepository.save(newProduct);

        //DB SAVE METHOD CONCLUDES

        return response;


    }

    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDTO productDTO) throws NotFoundException {
        Product product = Convert.ProductDtoToProduct(productDTO);
        Optional<Product> productOptional = productService.updateProduct(productId, product);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Can't Update Product, no product found with productId: "+productId);
        }
        return productOptional.get();
    }

    @PutMapping("/{productId}")
    public Product replaceProduct(@PathVariable("productId") Long productId, @RequestBody ProductDTO productDTO) throws NotFoundException {
        Product product = Convert.ProductDtoToProduct(productDTO);
        Optional<Product> productOptional = productService.replaceProduct(productId, product);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Can't Replace Product, no product found with productId: "+productId);
        }
        return productOptional.get();
    }

    @DeleteMapping("/{productId}")
    public Product deleteProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        Optional<Product> productOptional = productService.deleteProduct(productId);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Can't Delete Product, no product found with productId: "+productId);
        }
        return productOptional.get();
    }
}