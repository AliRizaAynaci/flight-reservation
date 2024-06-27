package com.flightreservation.dto;

import lombok.Data;

@Data
public class BookingDTO {

    private String bookingNumber;
    private String flightNumber;
    private String passengerName;
    private String email;
    private String phoneNumber;
}
