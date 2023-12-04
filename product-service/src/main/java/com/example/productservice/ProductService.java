package com.example.productservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    Map<String, Product> products = new HashMap<>();

    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());


    public void addProduct(Product product) {
        try {
            String id = generateId();
            product.setId(id);
            log.info("Adding new product {}", mapper.writeValueAsString(product));
            products.put(id, product);
        } catch (Exception e) {

        }
    }

    public List<Product> getAllProducts() {
        return products.values().stream().toList();
    }

    public List<Product> getProductsByCategory(Category category) {
        return products.values().stream().filter(p -> p.getCategory().equals(category)).collect(Collectors.toList());
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }

}
