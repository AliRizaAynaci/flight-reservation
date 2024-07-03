package com.flightreservation.service;

import com.flightreservation.dto.FlightDTO;
import com.flightreservation.dto.converter.FlightConverter;
import com.flightreservation.model.entity.Flight;
import com.flightreservation.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightConverter flightConverter;

    public Flight addFlight(FlightDTO flightDTO) {
        Flight flight = flightConverter.convertToEntity(flightDTO);
        return flightRepository.save(flight);
    }

    public Flight getFlightById(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Flight not found with by id : " + id));
        return flight;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight updateFlight(Long id, FlightDTO flightDTO) {
        Flight flight = flightRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Flight not found with by id : " + id));
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setCapacity(flightDTO.getCapacity());
        flight.setDepartureAirport(flightDTO.getDepartureAirport());
        flight.setArrivalAirport(flightDTO.getArrivalAirport());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        flight.setPrice(flightDTO.getPrice());
        flightRepository.save(flight);
        return flight;
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public List<FlightDTO> searchFlights(String departure, String arrival) {
        List<Flight> flights = flightRepository.findByDepartureAirportAndArrivalAirport(departure, arrival);
        return flights.stream().map(flightConverter::convertToDto).toList();
    }
}
