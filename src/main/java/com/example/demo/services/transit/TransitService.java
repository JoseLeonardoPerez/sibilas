package com.example.demo.services.transit;

import com.example.demo.models.PlanetDefinition;
import com.example.demo.models.PlanetPosition;
import com.example.demo.models.TransitAspect;
import com.example.demo.services.astrology.ZodiacCalculator;
import com.example.demo.services.astronomy.EphemerisService;
import org.springframework.stereotype.Service;
import swisseph.SweConst;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransitService {

    private final EphemerisService ephemerisService;

    public TransitService(
            EphemerisService ephemerisService
    ) {
        this.ephemerisService = ephemerisService;
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

    /*
     * CALCULA POSICIONES ACTUALES
     */
    public List<PlanetPosition> calculateCurrentTransits() {

        LocalDateTime now = LocalDateTime.now();

        return PLANETS.stream()
                .map(planetDefinition -> {

                    double degrees =
                            ephemerisService.getPlanetPosition(
                                    now,
                                    planetDefinition.getSwissEphCode()
                            );

                    return new PlanetPosition(
                            planetDefinition.getName(),
                            degrees,
                            ZodiacCalculator.calculateSign(degrees)
                    );
                })
                .toList();
    }

    /*
     * COMPARA TRÁNSITOS CON NATAL
     */
    public List<TransitAspect> compareWithNatal(

            List<PlanetPosition> natalPlanets,

            List<PlanetPosition> transitPlanets
    ) {

        List<TransitAspect> aspects =
                new ArrayList<>();

        for (PlanetPosition transit : transitPlanets) {

            for (PlanetPosition natal : natalPlanets) {

                double difference =
                        Math.abs(
                                transit.getDegrees()
                                        - natal.getDegrees()
                        );

                if (difference > 180) {

                    difference = 360 - difference;
                }

                String aspect =
                        detectAspect(difference);

                if (aspect != null) {

                    TransitAspect transitAspect =
                            new TransitAspect();

                    transitAspect.setTransitPlanet(
                            transit.getPlanet()
                    );

                    transitAspect.setNatalPlanet(
                            natal.getPlanet()
                    );

                    transitAspect.setAspect(aspect);

                    transitAspect.setOrb(
                            difference
                    );

                    aspects.add(transitAspect);
                }
            }
        }

        return aspects;
    }

    /*
     * DETECTA ASPECTOS
     */
    private String detectAspect(
            double difference
    ) {

        double orb = 6;

        if (Math.abs(difference - 0) <= orb) {

            return "Conjunción";
        }

        if (Math.abs(difference - 60) <= orb) {

            return "Sextil";
        }

        if (Math.abs(difference - 90) <= orb) {

            return "Cuadratura";
        }

        if (Math.abs(difference - 120) <= orb) {

            return "Trigono";
        }

        if (Math.abs(difference - 180) <= orb) {

            return "Oposición";
        }

        return null;
    }

    /*
     * CREA CONTEXTO PARA IA
     */
    public List<String> buildTransitContext(
            List<TransitAspect> aspects,
            List<PlanetPosition> currentTransits
    ) {

        List<String> context =
                new ArrayList<>();

        context.add(
                "TRANSITOS ACTUALES:"
        );

        for (PlanetPosition transit : currentTransits) {

            context.add(
                    transit.getPlanet()
                            + " en "
                            + transit.getSign()
            );
        }

        context.add(
                ""
        );

        context.add(
                "ASPECTOS ACTIVOS:"
        );

        for (TransitAspect aspect : aspects) {

            context.add(
                    aspect.getTransitPlanet()
                            + " "
                            + aspect.getAspect()
                            + " "
                            + aspect.getNatalPlanet()
            );
        }

        return context;
    }
}
