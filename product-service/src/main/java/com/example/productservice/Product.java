package com.example.productservice;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    String id;
    String name;
    String description;
    String summary;
    String sellerId;
    Category category;
    Double price;
    String image;
    List<Review> reviews;
}
