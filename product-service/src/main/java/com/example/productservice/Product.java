package com.example.productservice;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    String id;
    String name;
    String description;
    String sellerId;
    Category category;
    String image;
    List<Review> reviews;
}
