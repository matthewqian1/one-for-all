package org.example;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatCompletionRequest {
    private String model;
    private List<ChatCompletionMessage> messages;
}
