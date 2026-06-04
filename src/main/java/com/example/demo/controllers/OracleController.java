package com.example.demo.controllers;

import com.example.demo.dto.OracleRequest;
import com.example.demo.services.OracleService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class OracleController {

    private final OracleService oracleService;

    public OracleController(
            OracleService oracleService
    ) {
        this.oracleService = oracleService;
    }

    @PostMapping("/oracle")
    public String oracle(
            @RequestBody OracleRequest request
    ) {

        return oracleService
                .consultOracle(request);
    }
}