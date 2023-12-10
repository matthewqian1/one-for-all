package com.example.productservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
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
        InputStream stream1 = this.getClass().getClassLoader().getResourceAsStream("redShirt.jpg");
        byte[] fileContent = ByteStreams.toByteArray(stream1);
        String encodedString = Base64
                .getEncoder()
                .encodeToString(fileContent);

        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("data.json");
        List<Product> list = mapper.readValue(stream, new TypeReference<List<Product>>() {});
        int multiplier = (int) Math.ceil((double) 10 / list.size());
        List<Product> newList = new ArrayList<>();
        for (int i = 0; i < multiplier; i++) {
            newList.addAll(list);
        }
        newList.forEach(p -> p.setId(generateId()));
        newList.forEach(p -> p.setImage(encodedString));
        newList.forEach(p -> products.put(p.getId(), p));


        System.out.println();
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

    public Product getProduct(String id) {
        Product product = products.get(id);

        return products.get(id);
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
