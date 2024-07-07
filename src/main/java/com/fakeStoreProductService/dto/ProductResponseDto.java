package com.fakeStoreProductService.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProductResponseDto {

    int id;
    String title;
    int price;
    String description;
    String category;
    String image;
}
