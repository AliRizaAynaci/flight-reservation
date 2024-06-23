package com.flightreservation.dto;

public record BookingCreateDTO(
        String flightNumber,
        String name,
        String email,
        String phoneNumber
) {
}
