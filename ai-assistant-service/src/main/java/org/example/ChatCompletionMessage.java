package org.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatCompletionMessage {
    private String role;
    private String content;
}
