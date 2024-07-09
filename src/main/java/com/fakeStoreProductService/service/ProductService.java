package com.fakeStoreProductService.service;

import com.fakeStoreProductService.dto.ProductRequestDto;
import com.fakeStoreProductService.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product getAllProductsbyId(int id);

    List<Product> getAllProductsUpto(int limit);

    List<Product> getAllProductsbySorted(String sortingType);

    public Product addNewProduct(ProductRequestDto productRequestDto);

    public Product updateProduct(int id, ProductRequestDto productRequestDto);

    public Product deleteProduct(int id, ProductRequestDto productRequestDto);

    public void redisCheck();
}
