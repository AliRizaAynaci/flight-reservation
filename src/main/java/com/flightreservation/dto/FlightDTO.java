package com.flightreservation.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FlightDTO {

    private String flightNumber;
    private int capacity;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private BigDecimal price;

}
