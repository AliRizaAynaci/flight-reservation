package com.flightreservation.repository;

import com.flightreservation.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirportAndArrivalAirport(String departure, String arrival);

    Optional<Flight> findByFlightNumber(String flightNumber);
}
