package com.example.demo.services;

import com.example.demo.dto.OracleRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OracleService {

    private final OpenAIService openAIService;

    public OracleService(
            OpenAIService openAIService
    ) {
        this.openAIService = openAIService;
    }

    public String consultOracle(
            OracleRequest request
    ) {

        List<String> context =
                new ArrayList<>();

        context.add(
                "Nacimiento:"
        );

        context.add(
                "Fecha: " + request.getBirthDate()
        );

        context.add(
                "Hora: " + request.getBirthTime()
        );

        context.add(
                "Ciudad: " + request.getBirthCity()
        );

        context.add("");

        context.add(
                "Momento consultado:"
        );

        context.add(
                "Fecha: " + request.getTargetDate()
        );

        context.add(
                "Hora: " + request.getTargetTime()
        );

        context.add(
                "Ciudad: " + request.getTargetCity()
        );

        if (
                request.getQuestion() != null
                        &&
                        !request.getQuestion().isBlank()
        ) {

            context.add("");

            context.add(
                    "Pregunta:"
            );

            context.add(
                    request.getQuestion()
            );

        } else {

            context.add("");

            context.add(
                    "El usuario desea una lectura general de energías."
            );
        }

        return openAIService
                .generateOracleInterpretation(
                        context
                );
    }
}