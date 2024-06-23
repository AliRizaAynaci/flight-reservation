package com.flightreservation.dto;

public record UserRegistrationDTO(
        String name,
        String email,
        String phoneNumber,
        String password
) {
}
