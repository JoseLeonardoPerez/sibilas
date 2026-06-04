package com.example.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class OpenAIService {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;

    public OpenAIService() {

        this.webClient = WebClient.builder()
                .baseUrl("https://openrouter.ai/api/v1")
                .build();
    }

    public String generateInterpretation(
            List<String> symbolicContext
    ) {

        try {

            String prompt = """
                Eres una astróloga experta.

                Interpreta esta carta natal
                de forma psicológica,
                espiritual y moderna.

                CONTEXTO:
                """
                    + String.join("\n", symbolicContext);

            return sendRequest(prompt);

        } catch (Exception e) {

            e.printStackTrace();

            return "ERROR IA: " + e.getMessage();
        }
    }

    public String generateDailyInterpretation(
            List<String> context
    ) {

        try {

            String prompt = """
                Eres una astróloga moderna y espiritual.

                Analiza:
                - carta natal
                - tránsitos actuales
                - energía emocional del día

                Genera una lectura profunda,
                personalizada y en español.

                CONTEXTO:
                """
                    + String.join("\n", context);

            return sendRequest(prompt);

        } catch (Exception e) {

            e.printStackTrace();

            return "ERROR IA DAILY: " + e.getMessage();
        }
    }


    public String generateOracleInterpretation(
            List<String> context
    ) {

        try {

            String prompt = """
            Eres una IA astróloga,
            espiritual y simbólica.

            Analiza:
            - energía del momento
            - carta natal
            - tránsitos
            - simbolismo astrológico
            - contexto emocional y espiritual

            Genera una interpretación
            profunda y moderna en español.

            CONTEXTO:
            """
                    + String.join("\n", context);

            return sendRequest(prompt);

        } catch (Exception e) {

            e.printStackTrace();

            return "ERROR IA ORACLE: "
                    + e.getMessage();
        }
    }

    private String sendRequest(
            String prompt
    ) {

        Map<String, Object> requestBody = Map.of(

                "model", "openai/gpt-3.5-turbo",

                "messages", List.of(
                        Map.of(
                                "role", "user",
                                "content", prompt
                        )
                ),

                "temperature", 0.8
        );

        Map response = webClient.post()

                .uri("/chat/completions")

                .header(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + apiKey
                )

                .header(
                        "HTTP-Referer",
                        "http://localhost:5173"
                )

                .header(
                        "X-Title",
                        "Sibilas"
                )

                .contentType(MediaType.APPLICATION_JSON)

                .bodyValue(requestBody)

                .retrieve()

                .bodyToMono(Map.class)

                .block();

        List<Map<String, Object>> choices =
                (List<Map<String, Object>>) response.get("choices");

        Map<String, Object> choice =
                choices.get(0);

        Map<String, Object> message =
                (Map<String, Object>) choice.get("message");

        return message.get("content").toString();
    }
}