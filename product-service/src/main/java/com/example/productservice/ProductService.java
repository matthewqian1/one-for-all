package com.example.productservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        String id = generateId();
        product.setId(id);
        products.put(id, product);
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
