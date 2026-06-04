package com.example.demo.repositories;

import com.example.demo.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long> {

    Optional<House> findByNumber(Integer number);
}
