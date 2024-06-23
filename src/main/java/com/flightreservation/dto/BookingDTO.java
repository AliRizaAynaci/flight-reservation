package com.flightreservation.dto;

public record BookingDTO (
        String bookingNumber,
        String flightNumber,
        String passengerName,
        String email,
        String phoneNumber
){
}
