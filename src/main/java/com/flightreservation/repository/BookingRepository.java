package com.flightreservation.repository;

import com.flightreservation.model.entity.Booking;
import com.flightreservation.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    int countByFlight(Flight flight);
}
