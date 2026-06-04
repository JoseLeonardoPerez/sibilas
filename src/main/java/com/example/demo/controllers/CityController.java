package com.example.demo.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin("*")
public class CityController {

    @GetMapping("/cities")
    public ResponseEntity<String> searchCities(
            @RequestParam String query
    ) {

        String url =
                "https://nominatim.openstreetmap.org/search?q="
                        + query +
                        "&format=json&addressdetails=1&limit=5";

        RestTemplate restTemplate =
                new RestTemplate();

        HttpHeaders headers =
                new HttpHeaders();

        headers.set(
                "User-Agent",
                "SibilasApp/1.0"
        );

        HttpEntity<String> entity =
                new HttpEntity<>(headers);

        ResponseEntity<String> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        String.class
                );

        return response;
    }
}