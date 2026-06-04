package com.example.demo.services.astrology;

import com.example.demo.models.Aspect;
import com.example.demo.models.PlanetPosition;

import java.util.ArrayList;
import java.util.List;

public class AspectCalculator {

    public static List<Aspect> calculateAspects(
            List<PlanetPosition> planets
    ) {

        List<Aspect> aspects =
                new ArrayList<>();

        for (int i = 0;
             i < planets.size();
             i++) {

            for (int j = i + 1;
                 j < planets.size();
                 j++) {

                PlanetPosition p1 =
                        planets.get(i);

                PlanetPosition p2 =
                        planets.get(j);

                double difference =
                        Math.abs(
                                p1.getDegrees()
                                        -
                                        p2.getDegrees()
                        );

                if (difference > 180) {
                    difference =
                            360 - difference;
                }

                String aspectType =
                        detectAspect(
                                difference
                        );

                if (aspectType != null) {

                    aspects.add(
                            new Aspect(
                                    p1.getPlanet(),
                                    p2.getPlanet(),
                                    aspectType,
                                    difference
                            )
                    );
                }
            }
        }

        return aspects;
    }

    private static String detectAspect(
            double angle
    ) {

        double orb = 5;

        if (Math.abs(angle - 0) <= orb) {
            return "Conjunction";
        }

        if (Math.abs(angle - 60) <= orb) {
            return "Sextile";
        }

        if (Math.abs(angle - 90) <= orb) {
            return "Square";
        }

        if (Math.abs(angle - 120) <= orb) {
            return "Trine";
        }

        if (Math.abs(angle - 180) <= orb) {
            return "Opposition";
        }

        return null;
    }
}