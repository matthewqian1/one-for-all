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
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ProductService {
    Map<String, Product> products = new HashMap<>();

    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @PostConstruct
    public void load() throws IOException {

        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("products/productData.yml");
        Map<String, Object> obj = yaml.load(inputStream);

        obj.entrySet().forEach(e -> {
            try {
                String key = e.getKey();
                Product product = mapper.convertValue(e.getValue(), Product.class);
                product.setId(key);
                InputStream imageStream = this.getClass().getClassLoader().getResourceAsStream(String.format("products/images/%s.png",key));
                byte[] fileContent = ByteStreams.toByteArray(imageStream);
                String encodedString = Base64
                        .getEncoder()
                        .encodeToString(fileContent);
                product.setImage(encodedString);
                products.put(key, product);
            } catch (Exception exception) {
                log.error("Error loading products", exception);
            }
        });
    }


    public Product getProduct(String id) {
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

    public List<String> getImages(String[] ids) {
        return Stream.of(ids).map(id -> products.containsKey(id) ? products.get(id).getImage() : "").collect(Collectors.toList());
    }

    public List<String> getCategories() {
        return Arrays.stream(Category.values()).map(Category::getName).toList();
    }

    public List<String> getColours() {
        return Arrays.stream(Colour.values()).map(Colour::getName).toList();
    }

}
