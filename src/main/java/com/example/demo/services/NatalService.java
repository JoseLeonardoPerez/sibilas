package com.example.demo.services;

import com.example.demo.dto.BirthDataRequest;
import com.example.demo.models.*;
import com.example.demo.services.astrology.AspectCalculator;
import com.example.demo.services.astrology.HouseCalculator;
import com.example.demo.services.astrology.ZodiacCalculator;
import com.example.demo.services.astronomy.EphemerisService;
import com.example.demo.services.interpretation.InterpretationService;
import org.springframework.stereotype.Service;
import swisseph.SweConst;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class NatalService {

    private final EphemerisService ephemerisService;
    private final GeoService geoService;
    private final InterpretationService interpretationService;

    public NatalService(
            EphemerisService ephemerisService,
            GeoService geoService,
            InterpretationService interpretationService
    ) {
        this.ephemerisService = ephemerisService;
        this.geoService = geoService;
        this.interpretationService = interpretationService;
    }

    private static final List<PlanetDefinition> PLANETS = List.of(
            new PlanetDefinition("Sol", SweConst.SE_SUN),

            new PlanetDefinition("Luna", SweConst.SE_MOON),

            new PlanetDefinition("Mercurio", SweConst.SE_MERCURY),

            new PlanetDefinition("Venus", SweConst.SE_VENUS),

            new PlanetDefinition("Marte", SweConst.SE_MARS),

            new PlanetDefinition("Jupiter", SweConst.SE_JUPITER),

            new PlanetDefinition("Saturno", SweConst.SE_SATURN),

            new PlanetDefinition("Urano", SweConst.SE_URANUS),

            new PlanetDefinition("Neptuno", SweConst.SE_NEPTUNE),

            new PlanetDefinition("Pluton", SweConst.SE_PLUTO)
    );

    public NatalChart calculateNatalChart(BirthDataRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        LocalDate date = LocalDate.parse(request.getBirthDate());
        LocalTime time = LocalTime.parse(request.getBirthTime());
        LocalDateTime birthDateTime = LocalDateTime.of(date, time);

        GeoCoordinates coordinates =
                geoService.getCoordinates(
                        request.getCity()
                );

        double latitude = coordinates.getLatitude();
        double longitude = coordinates.getLongitude();

        double ascendantDegrees =
                ephemerisService.getAscendant(birthDateTime, latitude, longitude);

        double[] houseCusps =
                ephemerisService.getHouseCusps(birthDateTime, latitude, longitude);

        List<PlanetPosition> planets =
                PLANETS.stream()
                        .map(planetDefinition -> {

                            double degrees =
                                    ephemerisService.getPlanetPosition(
                                            birthDateTime,
                                            planetDefinition.getSwissEphCode()
                                    );

                            int house =
                                    HouseCalculator.findHouse(degrees, houseCusps);

                            PlanetPosition planet =
                                    new PlanetPosition(
                                            planetDefinition.getName(),
                                            degrees,
                                            ZodiacCalculator.calculateSign(degrees)
                                    );

                            planet.setHouse(house);
                            return planet;
                        })
                        .toList();

        List<Aspect> aspects =
                AspectCalculator.calculateAspects(planets);

        List<String> symbolicContext =
                interpretationService.buildSymbolicContext(planets);

        List<House> houses =
                IntStream.rangeClosed(1, 12)
                        .mapToObj(i -> new House(
                                i,
                                houseCusps[i],
                                ZodiacCalculator.calculateSign(houseCusps[i])
                        ))
                        .toList();

        NatalChart chart = new NatalChart();
        chart.setPlanets(planets);
        chart.setAscendant(ZodiacCalculator.calculateSign(ascendantDegrees));
        chart.setHouses(houses);
        chart.setAspects(aspects);
        chart.setSymbolicContext(
                symbolicContext
        );
        //chart.setInterpretations(interpretations);

        String aiInterpretation =
                interpretationService
                        .generateAIInterpretation(planets);

        chart.setAiInterpretation(aiInterpretation);

        return chart;
    }
}