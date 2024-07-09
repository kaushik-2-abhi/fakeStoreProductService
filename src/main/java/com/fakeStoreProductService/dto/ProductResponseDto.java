package com.fakeStoreProductService.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@JsonSerialize
public class ProductResponseDto {

    int id;
    String title;
    int price;
    String description;
    String category;
    String image;
}
