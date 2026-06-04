package com.example.demo.services;

import com.example.demo.models.LocationResponse;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationService {

    public LocationResponse getCoordinates(
            String city
    ) {

        city =
                city.toLowerCase();

        switch (city) {

            case "buenos aires":

                return new LocationResponse(
                        -34.6037,
                        -58.3816
                );

            case "tokyo":

                return new LocationResponse(
                        35.6764,
                        139.6500
                );

            case "london":

                return new LocationResponse(
                        51.5072,
                        -0.1276
                );

            case "new york":

                return new LocationResponse(
                        40.7128,
                        -74.0060
                );

            default:

                return new LocationResponse(
                        0,
                        0
                );
        }

    }

}
