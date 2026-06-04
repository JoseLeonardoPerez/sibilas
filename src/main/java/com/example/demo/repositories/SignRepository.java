package com.example.demo.repositories;


import com.example.demo.entities.Sign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignRepository extends JpaRepository<Sign, Long> {

    Optional<Sign> findByName(String name);
}
