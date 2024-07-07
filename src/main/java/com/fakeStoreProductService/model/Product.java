package com.fakeStoreProductService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    int id;
    String name;
    int price;
    String description;
    String category;
    String image;
}
