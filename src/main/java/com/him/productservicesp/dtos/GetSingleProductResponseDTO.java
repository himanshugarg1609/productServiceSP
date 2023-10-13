package com.him.productservicesp.dtos;

import com.him.productservicesp.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDTO {
    private Product product;
}
