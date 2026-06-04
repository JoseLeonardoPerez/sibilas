package com.example.demo.controllers;

import com.example.demo.dto.BirthDataRequest;
import com.example.demo.services.DailyService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/daily")
@CrossOrigin(origins = "*")
public class DailyController {

    private final DailyService dailyService;

    public DailyController(
            DailyService dailyService
    ) {
        this.dailyService = dailyService;
    }

    @PostMapping
    public Map<String, String> generateDailyReading(
            @RequestBody BirthDataRequest request
    ) {

        String interpretation =
                dailyService.generateDailyReading(
                        request
                );

        return Map.of(
                "reading",
                interpretation
        );
    }
}