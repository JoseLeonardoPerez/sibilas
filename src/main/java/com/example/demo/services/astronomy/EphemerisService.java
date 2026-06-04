package com.example.demo.services.astronomy;

import com.example.demo.services.GeoLocationService;
import org.springframework.stereotype.Service;
import swisseph.SweConst;
import swisseph.SwissEph;

import java.time.LocalDateTime;

@Service
public class EphemerisService {

    private final GeoLocationService geoLocationService;

    private final SwissEph swissEph;

    public EphemerisService(
            GeoLocationService geoLocationService
    ) {

        this.geoLocationService =
                geoLocationService;

        swissEph =
                new SwissEph();

        swissEph.swe_set_ephe_path(
                "./ephemeris"
        );
    }

    public double getPlanetPosition(
            LocalDateTime birthDateTime,
            int planet
    ) {

        double jd =
                JulianDayCalculator.calculate(
                        birthDateTime
                );

        double[] xx =
                new double[6];

        StringBuffer serr =
                new StringBuffer();

        swissEph.swe_calc(
                jd,
                planet,
                SweConst.SEFLG_SWIEPH,
                xx,
                serr
        );

        return xx[0];
    }

    public boolean isRetrograde(
            LocalDateTime birthDateTime,
            int planetCode
    ) {

        double jd =
                JulianDayCalculator.calculate(
                        birthDateTime
                );

        double[] xx =
                new double[6];

        StringBuffer serr =
                new StringBuffer();

        swissEph.swe_calc(
                jd,
                planetCode,
                SweConst.SEFLG_SWIEPH,
                xx,
                serr
        );

        return xx[3] < 0;
    }

    public double getAscendant(
            LocalDateTime birthDateTime,
            double latitude,
            double longitude
    ) {

        double jd =
                JulianDayCalculator.calculate(
                        birthDateTime
                );

        double[] cusps =
                new double[13];

        double[] ascmc =
                new double[10];

        swissEph.swe_houses(
                jd,
                SweConst.SEFLG_SWIEPH,
                latitude,
                longitude,
                'P',
                cusps,
                ascmc
        );

        return ascmc[0];
    }

    public double[] getHouseCusps(
            LocalDateTime birthDateTime,
            double latitude,
            double longitude
    ) {

        double jd =
                JulianDayCalculator.calculate(
                        birthDateTime
                );

        double[] cusps =
                new double[13];

        double[] ascmc =
                new double[10];

        swissEph.swe_houses(
                jd,
                SweConst.SEFLG_SWIEPH,
                latitude,
                longitude,
                'P',
                cusps,
                ascmc
        );

        return cusps;
    }

}