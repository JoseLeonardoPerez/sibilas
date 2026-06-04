package com.example.demo.services.astrology;

public class HouseCalculator {

    public static int findHouse(
            double planetDegree,
            double[] houseCusps
    ) {

        for (int i = 1; i <= 12; i++) {

            double start =
                    houseCusps[i];

            double end;

            if (i == 12) {
                end = houseCusps[1];
            } else {
                end = houseCusps[i + 1];
            }

            if (isBetween(
                    planetDegree,
                    start,
                    end
            )) {

                return i;
            }
        }

        return -1;
    }

    private static boolean isBetween(
            double value,
            double start,
            double end
    ) {

        if (start < end) {

            return value >= start
                    &&
                    value < end;
        }

        return value >= start
                ||
                value < end;
    }
}