package org.example;

import lombok.Data;

import javax.annotation.Nullable;

@Data
public class UserQuery {
    @Nullable
    private QueryType type;
    private String query;
}
