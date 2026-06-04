package com.example.demo.services.interpretation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiInterpretationService {

    public String buildPrompt(List<String> symbolicData) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                Interpretá esta carta natal de forma psicológica,
                espiritual y moderna.

                Datos simbólicos:
                """);

        for (String s : symbolicData) {
            prompt.append("- ").append(s).append("\n");
        }

        prompt.append("""
                
                Generá una interpretación profunda,
                clara y humana.
                """);

        return prompt.toString();
    }
}
