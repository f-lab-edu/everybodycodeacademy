package com.everycodeacademy.backend.review.openai;

import com.everycodeacademy.backend.review.dto.AiFeedbackResponse;
import com.everycodeacademy.backend.review.entity.CodeSubmission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class OpenAiReviewClient {

    private final RestClient restClient;
    private final String apiKey;
    private final String model;

    public OpenAiReviewClient(
            RestClient.Builder restClientBuilder,
            @Value("${openai.base-url}") String baseUrl,
            @Value("${openai.api-key:}") String apiKey,
            @Value("${openai.model:gpt-4o-mini}") String model
    ) {
        this.restClient = restClientBuilder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
        this.model = model;
    }

    public AiFeedbackResponse generateFeedback(Long submissionId, CodeSubmission submission, String focus) {
        if (apiKey == null || apiKey.isBlank()) {
            return new AiFeedbackResponse(submissionId, model, "OPENAI_API_KEY is not configured.", true);
        }

        String prompt = "Review this code for " + ((focus == null || focus.isBlank()) ? "overall quality" : focus)
                + "\nLanguage: " + submission.getLanguage() + "\nCode:\n" + submission.getSourceCode();

        Map<String, Object> body = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", "You are a strict senior code reviewer."),
                        Map.of("role", "user", "content", prompt)
                ),
                "temperature", 0.2
        );

        try {
            Map<String, Object> response = restClient.post()
                    .uri("/chat/completions")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(Map.class);
            return new AiFeedbackResponse(submissionId, model, extractContent(response), false);
        } catch (Exception e) {
            return new AiFeedbackResponse(submissionId, model, "OpenAI request failed: " + e.getMessage(), true);
        }
    }

    @SuppressWarnings("unchecked")
    private String extractContent(Map<String, Object> response) {
        if (response == null) return "No response body from OpenAI.";
        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
        if (choices == null || choices.isEmpty()) return "No choices in OpenAI response.";
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
        if (message == null || message.get("content") == null) return "No message content in OpenAI response.";
        return String.valueOf(message.get("content"));
    }
}
