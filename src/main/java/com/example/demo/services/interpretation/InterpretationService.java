package com.example.demo.services.interpretation;

import com.example.demo.entities.*;
import com.example.demo.models.PlanetPosition;
import com.example.demo.repositories.*;
import org.springframework.stereotype.Service;
import com.example.demo.services.OpenAIService;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterpretationService {

    private final PlanetRepository planetRepository;

    private final SignRepository signRepository;

    private final HouseRepository houseRepository;

    private final PlanetSignMeaningRepository
            planetSignMeaningRepository;

    private final PlanetHouseMeaningRepository
            planetHouseMeaningRepository;
    private final OpenAIService openAIService;

    public InterpretationService(

            PlanetRepository planetRepository,

            SignRepository signRepository,

            HouseRepository houseRepository,

            PlanetSignMeaningRepository
                    planetSignMeaningRepository,

            PlanetHouseMeaningRepository
                    planetHouseMeaningRepository,
            OpenAIService openAIService) {

        this.planetRepository =
                planetRepository;

        this.signRepository =
                signRepository;

        this.houseRepository =
                houseRepository;

        this.planetSignMeaningRepository =
                planetSignMeaningRepository;

        this.planetHouseMeaningRepository =
                planetHouseMeaningRepository;
        this.openAIService = openAIService;
    }

    public List<String> buildSymbolicContext(
            List<PlanetPosition> planets
    ) {

        List<String> symbolicContext = new ArrayList<>();

        for (PlanetPosition p : planets) {

            Planet planet =
                    planetRepository.findByName(p.getPlanet())
                            .orElseThrow();

            Sign sign =
                    signRepository.findByName(p.getSign())
                            .orElseThrow();

            House house =
                    houseRepository.findByNumber(p.getHouse())
                            .orElseThrow();

            symbolicContext.add(
                    p.getPlanet()
                            + " en "
                            + p.getSign()
                            + " en casa "
                            + p.getHouse()
            );

            symbolicContext.add(
                    p.getPlanet()
                            + " representa "
                            + planet.getKeywords()
            );

            symbolicContext.add(
                    p.getSign()
                            + " representa "
                            + sign.getKeywords()
            );

            symbolicContext.add(
                    "Casa "
                            + p.getHouse()
                            + " representa "
                            + house.getKeywords()
            );
        }

        return symbolicContext;
    }

    public String generateAIInterpretation(
            List<PlanetPosition> planets
    ) {

        List<String> context =
                buildSymbolicContext(planets);

        return openAIService
                .generateInterpretation(context);
    }

}