package com.fakeStoreProductService.service;

import com.fakeStoreProductService.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product getAllProductsbyId(int id);

    List<Product> getAllProductsUpto(int limit);
}
