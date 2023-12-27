package org.example;

import lombok.Data;

@Data
public class UserQuery {
    private QueryType type;
    private String query;
}
