package org.example;

import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
public class ApiService {
    RestTemplate template = new RestTemplate();
    Gson gson = new Gson();
    String url = "https://api.openai.com/v1/chat/completions";

    private HttpEntity<String> generateRequest(UserQuery query) {
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(List.of(ChatCompletionMessage.builder()
                                .role("system")
                                .content("You are a helpful ecommerce assistant. Generate sample responses to user queries")
                                .build(),
                        ChatCompletionMessage.builder()
                                .role("user")
                                .content(query.getQuery())
                                .build()
                ))
                .build();
        HttpHeaders headers = new HttpHeaders();
        String openAiKey = System.getenv().get("CHAT_GPT_API_KEY");
        headers.add("Authorization", "Bearer " + openAiKey);
        headers.add("Content-Type", "application/json");
        return new HttpEntity<>(gson.toJson(request), headers);
    }

    public String generateResponse(UserQuery query) {
        try {
            ResponseEntity<String> responseEntity = template.exchange(URI.create(url), HttpMethod.POST, generateRequest(query), String.class);
            return gson.fromJson(responseEntity.getBody(), ChatCompletionResponse.class).getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            log.error("Error processing ai api request", e);
        }

        return "We are unable to process your query request at this time";
    }
}
