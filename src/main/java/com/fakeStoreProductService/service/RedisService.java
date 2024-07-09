package com.fakeStoreProductService.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T getKey(String key, Class<T> entityClass) {

        try {
            Object object = redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue((JsonParser) object,entityClass);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;

    }

    public void setKey(String key, Object value, int ttl){
//        System.out.println("Setting");
//        System.out.println(key);
//        System.out.println(value.toString());
//        System.out.println(ttl);
        redisTemplate.opsForValue().set(key, value ,ttl, TimeUnit.SECONDS);



    }
}
