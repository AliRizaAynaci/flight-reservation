package com.flightreservation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightDTO(
        String flightNumber,
        String departureAirport,
        String arrivalAirport,
        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        BigDecimal price
) {
}
