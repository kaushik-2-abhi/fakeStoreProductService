package com.fakeStoreProductService.controller;

import com.fakeStoreProductService.dto.ProductRequestDto;
import com.fakeStoreProductService.model.Product;
import com.fakeStoreProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productservice")
public class ProductServiceController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getAllProductsById(@PathVariable("id") int Id) {

        return productService.getAllProductsbyId(Id);
    }

    //TODO: need to implement sorting and limit together
    @GetMapping("/product")
    public List<Product> getProductsUpto(@RequestParam(value = "Limit", required = false) Integer limit,
                                         @RequestParam(value = "sort", required = false) String sortingType) {

        if (sortingType == null) {

            return productService.getAllProductsUpto(limit);
        } else return productService.getAllProductsbySorted(sortingType);


    }

    @PostMapping("products")
    public Product addNewProduct(@RequestBody ProductRequestDto productRequestDto) {

        return productService.addNewProduct(productRequestDto);


    }

   @PutMapping("/products/{Id}")
    public Product updateProduct(@PathVariable ("Id") int id, @RequestBody ProductRequestDto productRequestDto){
        return productService.updateProduct(id,productRequestDto);
   }

   @DeleteMapping("/products/{Id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable ("Id") int id){

        ProductRequestDto productRequestDto=null;
        return new ResponseEntity<>(productService.deleteProduct(id,productRequestDto), HttpStatus.OK) ;
   }




}
