package com.example.bookingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bookingapp.models.Seat;
import com.example.bookingapp.models.Theatre;
import com.example.bookingapp.repositories.SeatRepository;
import com.example.bookingapp.repositories.TheatreRepository;

import java.util.*;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private SeatRepository seatRepository;

    @PostMapping("/add")
    public String addTheatre(@RequestBody Theatre theatre) {
        theatreRepository.save(theatre);
        return "Theatre added!";
    }

    @GetMapping("/search")
    public List<Theatre> searchByLocation(@RequestParam String location) {
        return theatreRepository.findByLocationContainingIgnoreCase(location);
    }

    @GetMapping("/{id}")
    public Optional<Theatre> getTheatre(@PathVariable int id) {
        return theatreRepository.findById(id);
    }

    @PostMapping("/book-seat/{seatId}")
    public String bookSeat(@PathVariable int seatId) {
        Optional<Seat> optionalSeat = seatRepository.findById(seatId);
        if (optionalSeat.isPresent()) {
            Seat seat = optionalSeat.get();
            if (seat.isBooked() || seat.isPath()) return "Cannot book this seat.";
            seat.setBooked(true);
            seatRepository.save(seat);
            return "Seat booked successfully!";
        }
        return "Seat not found.";
    }

    @GetMapping("/seats/{theatreId}")
    public List<Seat> getSeats(@PathVariable int theatreId) {
        return seatRepository.findByTheatreId(theatreId);
    }
}
