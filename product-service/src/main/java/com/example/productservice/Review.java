package com.example.productservice;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Review {
    int rating;
    int username;
    int comment;
    LocalDate date;
}
