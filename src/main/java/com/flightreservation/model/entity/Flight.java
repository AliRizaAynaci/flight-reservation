package com.flightreservation.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private int capacity;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private BigDecimal price;
}
