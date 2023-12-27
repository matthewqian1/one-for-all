package org.example;

import com.fasterxml.jackson.annotation.JsonValue;

public enum QueryType {
    PRODUCT("Product"),
    FEEDBACK("Feedback"),
    SUPPORT("Support");

    final String name;
    QueryType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
