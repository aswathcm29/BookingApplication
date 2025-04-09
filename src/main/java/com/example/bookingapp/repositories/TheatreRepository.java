package com.example.bookingapp.repositories;

import com.example.bookingapp.models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
    List<Theatre> findByLocationContainingIgnoreCase(String location);
}


