package com.fakeStoreProductService.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize
public class Product implements Serializable {

    int id;
    String name;
    int price;
    String description;
    String category;
    String image;
}
