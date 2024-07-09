package com.fakeStoreProductService.service.impl;

import com.fakeStoreProductService.dto.ProductRequestDto;
import com.fakeStoreProductService.dto.ProductResponseDto;
import com.fakeStoreProductService.model.Product;
import com.fakeStoreProductService.service.ProductService;
import com.fakeStoreProductService.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductResponseDto productResponseDto;
    @Autowired
    private RedisService redisService;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public List<Product> getAllProducts() {

        //initialising product list and redis template


        List<Product> products = new ArrayList<>();
        HashOperations<String, String, Product> hashOps = redisTemplate.opsForHash();
        int counter = 1;

        //checking if total number of product count present in redis
        if (hashOps.hasKey("ProductsCount", "counter")) {

            counter = (int) redisTemplate.opsForHash().get("ProductsCount", "counter");

        }
        counter = counter - 1;

        //check if product is present in resdis if yes then get
        //the products from it while the total number of products has been found
        //return the product list from here itself.


        if (hashOps.hasKey("allProducts", "product" + counter)) {
            while (counter > 0) {
                Product product = hashOps.get("allProducts", "product" + counter);
                products.add(product);
                counter--;
            }
            return products;
        }

        //if products are not found in redis get the all products form fake store by using
        //Rest template
        ProductResponseDto productResponseDto[] = restTemplate.getForObject("https://fakestoreapi.com/products", ProductResponseDto[].class);

        counter = 1;

        for (ProductResponseDto p : productResponseDto) {
            products.add(getProductfromDto(p));
            if (!products.isEmpty()) {

                //saving the products in redis
                hashOps.put("allProducts", "product" + counter, products.get(products.size() - 1));
                redisTemplate.expire("allProducts", 10, TimeUnit.SECONDS);
                //hashOps.getOperations().expire("allProducts",10,TimeUnit.SECONDS);

            }

            counter++;
        }

        //saving the all products count in redis
        redisTemplate.opsForHash().put("ProductsCount", "counter", counter);

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
            products.add(getProductfromDto(p));
        }
        return products;
    }

    @Override
    public List<Product> getAllProductsbySorted(String sortingType) {

        ProductResponseDto productResponseDto[] = restTemplate.getForObject("https://fakestoreapi.com/products?sort=?" + sortingType, ProductResponseDto[].class);

        List<Product> products = new ArrayList<>();
        for (ProductResponseDto p : productResponseDto) {
            products.add(getProductfromDto(p));
        }
        return products;
    }

    @Override
    public Product addNewProduct(ProductRequestDto productRequestDto) {

        productResponseDto = restTemplate.postForObject("https://fakestoreapi.com/products", productRequestDto, ProductResponseDto.class);
        return getProductfromDto(productResponseDto);
    }

    @Override
    public Product updateProduct(int id, ProductRequestDto productRequestDto) {

        RequestCallback requestCallback = this.restTemplate.httpEntityCallback(productRequestDto, ProductResponseDto.class);
        HttpMessageConverterExtractor<ProductResponseDto> responseExtractor = new HttpMessageConverterExtractor(ProductResponseDto.class, this.restTemplate.getMessageConverters());
        productResponseDto = this.restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return getProductfromDto(productResponseDto);

    }

    @Override
    public Product deleteProduct(int id, ProductRequestDto productRequestDto) {
        RequestCallback requestCallback = this.restTemplate.httpEntityCallback(productRequestDto, ProductResponseDto.class);
        HttpMessageConverterExtractor<ProductResponseDto> responseExtractor = new HttpMessageConverterExtractor(ProductResponseDto.class, this.restTemplate.getMessageConverters());
        productResponseDto = this.restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.DELETE, requestCallback, responseExtractor);

        // productResponseDto= restTemplate.delete("https://fakestoreapi.com/products/{Id}",id );

        return getProductfromDto(productResponseDto);
    }


    public Product getProductfromDto(ProductResponseDto productResponseDto) {

        Product product = Product.builder()
                .id(productResponseDto.getId())
                .price(productResponseDto.getPrice())
                .name(productResponseDto.getTitle())
                .category(productResponseDto.getCategory())
                .description(productResponseDto.getDescription())
                .image(productResponseDto.getImage())
                .build();
        return product;


    }

    public void redisCheck() {

        if (redisTemplate.opsForHash().hasKey("From intellij", "Hi")) {
            System.out.println(redisTemplate.opsForHash().get("From intellij", "Hi").toString());
        } else {
            redisTemplate.opsForHash().put("From intellij", "Hi", "working");
        }
    }


}
