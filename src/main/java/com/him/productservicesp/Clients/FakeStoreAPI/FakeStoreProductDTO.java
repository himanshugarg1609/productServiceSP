package com.him.productservicesp.Clients.FakeStoreAPI;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDTO {
    private long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
