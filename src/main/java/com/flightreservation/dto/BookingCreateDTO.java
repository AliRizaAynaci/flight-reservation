package com.flightreservation.dto;

import lombok.Data;

@Data
public class BookingCreateDTO {

    private String flightNumber;
    private String passengerName;
    private String passengerEmail;
    private String passengerPhone;
}
