package com.fakeStoreProductService.controller;

import com.fakeStoreProductService.model.Product;
import com.fakeStoreProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productservice")
public class ProductServiceController {

  @Autowired
  private ProductService productService;

@GetMapping("/products")
public List<Product> getAllProducts(){
    return productService.getAllProducts();
}

@GetMapping("/products/{id}")
    public Product getAllProductsById(@PathVariable("id") int Id){

    return productService.getAllProductsbyId(Id);
}

@GetMapping("/product")
    public List<Product> getProductsUpto(@RequestParam("Limit") int limit){

    return productService.getAllProductsUpto(limit);

}


}
