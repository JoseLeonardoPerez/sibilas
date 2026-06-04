package com.example.demo.repositories;

import com.example.demo.entities.Planet;
import com.example.demo.entities.PlanetSignMeaning;
import com.example.demo.entities.Sign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetSignMeaningRepository
        extends JpaRepository<PlanetSignMeaning, Long> {

    Optional<PlanetSignMeaning>
    findByPlanetAndSign(
            Planet planet,
            Sign sign
    );
}