package com.example.productservice;

import lombok.Data;

@Data
public class Product {
    String id;
    String name;
    String description;
    Category category;
    String image;
}
