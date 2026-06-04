package com.example.demo.services.astrology;

public class ZodiacCalculator {

    public static String calculateSign(double degrees) {

        if (degrees >= 0 && degrees < 30)
            return "Aries";

        if (degrees >= 30 && degrees < 60)
            return "Tauro";

        if (degrees >= 60 && degrees < 90)
            return "Geminis";

        if (degrees >= 90 && degrees < 120)
            return "Cancer";

        if (degrees >= 120 && degrees < 150)
            return "Leo";

        if (degrees >= 150 && degrees < 180)
            return "Virgo";

        if (degrees >= 180 && degrees < 210)
            return "Libra";

        if (degrees >= 210 && degrees < 240)
            return "Escorpio";

        if (degrees >= 240 && degrees < 270)
            return "Sagitario";

        if (degrees >= 270 && degrees < 300)
            return "Capricornio";

        if (degrees >= 300 && degrees < 330)
            return "Acuario";

        return "Piscis";
    }
}