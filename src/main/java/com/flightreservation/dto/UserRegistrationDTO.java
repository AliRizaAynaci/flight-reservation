package com.flightreservation.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {

    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}

