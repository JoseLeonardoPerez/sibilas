package com.example.demo.services.astronomy;

import java.time.LocalDateTime;

public class JulianDayCalculator {

    public static double calculate(
            LocalDateTime dateTime
    ) {

        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();

        double day =
                dateTime.getDayOfMonth()
                        +
                        (
                                dateTime.getHour()
                                        / 24.0
                        )
                        +
                        (
                                dateTime.getMinute()
                                        / 1440.0
                        );

        if (month <= 2) {
            year--;
            month += 12;
        }

        int a = year / 100;

        int b = 2 - a + (a / 4);

        return Math.floor(
                365.25 * (year + 4716)
        )
                +
                Math.floor(
                        30.6001 * (month + 1)
                )
                +
                day
                +
                b
                -
                1524.5;
    }
}