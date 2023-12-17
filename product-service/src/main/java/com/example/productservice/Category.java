package com.example.productservice;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    T_SHIRT("T shirt"),
    JACKET("Jacket"),
    SWEATER("Sweater");

    final String name;
    Category(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

}
