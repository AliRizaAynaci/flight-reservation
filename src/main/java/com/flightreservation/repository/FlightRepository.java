package com.flightreservation.repository;

import com.flightreservation.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    void deleteByFlightNumber(String flightNumber);
}
