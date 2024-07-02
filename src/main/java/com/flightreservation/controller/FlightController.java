package com.flightreservation.controller;

import com.flightreservation.dto.FlightDTO;
import com.flightreservation.model.entity.Flight;
import com.flightreservation.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addFlights")
    public ResponseEntity<Flight> addFlight(@RequestBody FlightDTO flightDTO) {
        Flight flight = flightService.addFlight(flightDTO);
        return ResponseEntity.ok(flight);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDTO) {
        Flight flight = flightService.updateFlight(id, flightDTO);
        return ResponseEntity.ok(flight);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.ok("Flight with id " + id + " has been successfully deleted.");
    }
}
