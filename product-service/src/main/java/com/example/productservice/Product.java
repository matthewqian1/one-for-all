package com.example.productservice;

import lombok.Data;
import org.example.Review;

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
    Colour colour;
    List<Review> reviews;
}
