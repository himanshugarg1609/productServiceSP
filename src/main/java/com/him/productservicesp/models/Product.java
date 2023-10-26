package com.him.productservicesp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private double price;
    private String description;
    @ManyToOne
    private Category category;
    private String imageUrl;

}
