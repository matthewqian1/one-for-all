package org.example;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Review {
    int rating;
    String username;
    String comment;
    LocalDate date;
}
