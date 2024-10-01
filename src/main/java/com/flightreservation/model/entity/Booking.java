package com.flightreservation.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookingNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Flight flight;

}
