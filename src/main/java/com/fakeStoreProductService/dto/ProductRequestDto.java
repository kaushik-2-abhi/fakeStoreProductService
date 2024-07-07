package com.fakeStoreProductService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductRequestDto {

    int id;
    String title;
    int price;
    String category;
    String description;
    String image;
}
