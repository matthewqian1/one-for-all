package org.example;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Review {
    int rating;
    int username;
    String comment;
    LocalDate date;
}
