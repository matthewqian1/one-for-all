package com.example.productservice;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Colour {
    BLUE("Blue"),
    BLACK("Black"),
    RED("Red"),
    GREEN("Green"),
    BROWN("Brown");

    final String name;
    Colour(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
