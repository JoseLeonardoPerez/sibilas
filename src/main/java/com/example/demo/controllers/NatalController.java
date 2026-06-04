
package com.example.demo.controllers;

import com.example.demo.dto.BirthDataRequest;
import com.example.demo.models.NatalChart;
import com.example.demo.services.NatalService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class NatalController {

    private final NatalService natalService;

    public NatalController(NatalService natalService) {
        this.natalService = natalService;
    }

    @PostMapping("/natal")
    public NatalChart natal(
            @RequestBody BirthDataRequest request) {

        return natalService.calculateNatalChart(request);
    }
}

