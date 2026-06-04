package com.example.demo.repositories;

import com.example.demo.entities.House;
import com.example.demo.entities.Planet;
import com.example.demo.entities.PlanetHouseMeaning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetHouseMeaningRepository
        extends JpaRepository<PlanetHouseMeaning, Long> {

    Optional<PlanetHouseMeaning>
    findByPlanetAndHouse(
            Planet planet,
            House house
    );
}