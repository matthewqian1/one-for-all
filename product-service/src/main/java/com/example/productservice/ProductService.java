package com.example.productservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    Map<String, Product> products = new HashMap<>();

    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @PostConstruct
    public void load() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("data.json");
        List<Product> productList = mapper.readValue(stream, new TypeReference<List<Product>>() {});
        productList.forEach(p -> products.put(generateId(), p));
    }

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
        List<Product> list = products.values().stream().toList();
        int multiplier = (int) Math.ceil((double) 10 / list.size());
        List<Product> newList = new ArrayList<>();
        for (int i = 0; i < multiplier; i++) {
            newList.addAll(list);
        }
        return newList;
    }

    public List<Product> getProductsByCategory(Category category) {
        return products.values().stream().filter(p -> p.getCategory().equals(category)).collect(Collectors.toList());
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }

}
