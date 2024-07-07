package com.fakeStoreProductService.service.impl;

import com.fakeStoreProductService.dto.ProductRequestDto;
import com.fakeStoreProductService.dto.ProductResponseDto;
import com.fakeStoreProductService.model.Product;
import com.fakeStoreProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductResponseDto productResponseDto;


    @Override
    public List<Product> getAllProducts() {

        ProductResponseDto productResponseDto[] = restTemplate.getForObject("https://fakestoreapi.com/products", ProductResponseDto[].class);

        List<Product> products = new ArrayList<>();
        for (ProductResponseDto p : productResponseDto) {

            products.add(getProductfromDto(p));
        }
        return products;
    }

    @Override
    public Product getAllProductsbyId(int id) {

        //  productResponseDto=restTemplate.getForObject("https://fakestoreapi.com/products/"+id , ProductResponseDto.class, id);
        productResponseDto = restTemplate.getForObject("https://fakestoreapi.com/products/{id}", ProductResponseDto.class, id);
        return getProductfromDto(productResponseDto);
    }

    @Override
    public List<Product> getAllProductsUpto(int limit) {
        ProductResponseDto[] productResponseDto = restTemplate.getForObject("https://fakestoreapi.com/products?limit=" + limit, ProductResponseDto[].class);
        List<Product> products = new ArrayList<>();
        for (ProductResponseDto p : productResponseDto) {
            products.add(getProductfromDto(p)) ;
        }
        return products;
    }

    public Product getProductfromDto(ProductResponseDto productResponseDto) {

        Product product;
        product = Product.builder()
                .id(productResponseDto.getId())
                .price(productResponseDto.getPrice())
                .name(productResponseDto.getTitle())
                .category(productResponseDto.getCategory())
                .description(productResponseDto.getDescription())
                .image(productResponseDto.getImage())
                .build();
        return product;
    }
}
