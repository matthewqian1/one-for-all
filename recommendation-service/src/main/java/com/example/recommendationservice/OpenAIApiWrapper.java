package com.example.recommendationservice;

import org.springframework.beans.factory.annotation.Value;

public class OpenAIApiWrapper {

    @Value("${open.ai.api.key}")
    String apiKey;
}
