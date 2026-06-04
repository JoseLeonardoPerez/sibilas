package com.example.demo.services;

import com.example.demo.models.GeoCoordinates;
import org.springframework.stereotype.Service;

@Service
public class GeoService {

    public GeoCoordinates getCoordinates(
            String city
    ) {

        city =
                city.toLowerCase();

        switch (city) {

            case "buenos aires":

                return new GeoCoordinates(
                        -34.6037,
                        -58.3816
                );

            case "tokyo":

                return new GeoCoordinates(
                        35.6764,
                        139.6500
                );

            case "london":

                return new GeoCoordinates(
                        51.5072,
                        -0.1276
                );

            case "new york":

                return new GeoCoordinates(
                        40.7128,
                        -74.0060
                );

            case "madrid":

                return new GeoCoordinates(
                        40.4168,
                        -3.7038
                );

            default:

                return new GeoCoordinates(
                        0,
                        0
                );
        }
    }
}