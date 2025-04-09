package com.example.bookingapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookingapp.models.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByTheatreId(int theatreId);
}
