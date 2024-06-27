package com.flightreservation.dto;

import lombok.Data;

@Data
public class BookingCreateDTO {

    private String flightNumber;
    private String name;
    private String email;
    private String phoneNumber;
}
