package com.example.demo.services;

import com.example.demo.dto.BirthDataRequest;
import com.example.demo.models.PlanetPosition;
import com.example.demo.models.TransitAspect;
import com.example.demo.models.NatalChart;
import com.example.demo.services.interpretation.InterpretationService;
import com.example.demo.services.transit.TransitService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DailyService {

    private final NatalService natalService;

    private final TransitService transitService;

    private final OpenAIService openAIService;

    private final InterpretationService interpretationService;

    public DailyService(

            NatalService natalService,

            TransitService transitService,

            OpenAIService openAIService,

            InterpretationService interpretationService
    ) {

        this.natalService = natalService;

        this.transitService = transitService;

        this.openAIService = openAIService;

        this.interpretationService = interpretationService;
    }

    public String generateDailyReading(
            BirthDataRequest request
    ) {

        /*
         * CARTA NATAL
         */
        NatalChart natalChart =
                natalService.calculateNatalChart(request);

        List<PlanetPosition> natalPlanets =
                natalChart.getPlanets();

        /*
         * TRÁNSITOS ACTUALES
         */
        List<PlanetPosition> currentTransits =
                transitService.calculateCurrentTransits();

        /*
         * ASPECTOS
         */
        List<TransitAspect> transitAspects =
                transitService.compareWithNatal(
                        natalPlanets,
                        currentTransits
                );

        /*
         * CONTEXTO NATAL
         */
        List<String> natalContext =
                interpretationService
                        .buildSymbolicContext(
                                natalPlanets
                        );

        /*
         * CONTEXTO TRÁNSITOS
         */
        List<String> transitContext =
                transitService.buildTransitContext(
                        transitAspects,
                        currentTransits
                );

        /*
         * CONTEXTO TOTAL
         */
        List<String> fullContext =
                new ArrayList<>();

        fullContext.add(
                "CARTA NATAL:"
        );

        fullContext.addAll(
                natalContext
        );

        fullContext.add("");

        fullContext.add(
                "ENERGIAS DEL DIA:"
        );

        fullContext.addAll(
                transitContext
        );

        /*
         * IA
         */
        return openAIService
                .generateDailyInterpretation(
                        fullContext
                );
    }
}
