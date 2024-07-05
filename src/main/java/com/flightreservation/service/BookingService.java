package com.flightreservation.service;

import com.flightreservation.dto.BookingCreateDTO;
import com.flightreservation.dto.BookingDTO;
import com.flightreservation.dto.converter.BookingConverter;
import com.flightreservation.exception.BookingException;
import com.flightreservation.exception.FlightNotFoundException;
import com.flightreservation.model.entity.Booking;
import com.flightreservation.model.entity.Flight;
import com.flightreservation.model.entity.User;
import com.flightreservation.model.enums.Role;
import com.flightreservation.repository.BookingRepository;
import com.flightreservation.repository.FlightRepository;
import com.flightreservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final BookingConverter bookingConverter;

    @Transactional
    public BookingDTO createBooking(BookingCreateDTO bookingCreateDTO) {
        Flight flight = flightRepository.findByFlightNumber(bookingCreateDTO.getFlightNumber())
                .orElseThrow(() -> new FlightNotFoundException(
                        "Flight not found with flight number: " + bookingCreateDTO.getFlightNumber()));

        if (!isFlightAvailable(flight)) {
            throw new BookingException("Flight is fully booked.");
        }

        User user = userRepository.findByEmail(bookingCreateDTO.getPassengerEmail())
                .orElseGet(() -> createUser(bookingCreateDTO));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingNumber(generateBookingNumber());
        bookingRepository.save(booking);

        BookingDTO bookingDTO = bookingConverter.convertToDto(booking);
        bookingDTO.setPassengerName(user.getName());
        bookingDTO.setPassengerEmail(user.getEmail());
        bookingDTO.setPassengerPhone(user.getPhoneNumber());

        return bookingDTO;
    }

    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = bookingConverter.convertToDto(booking);
            bookingDTO.setPassengerName(booking.getUser().getName());
            bookingDTO.setPassengerEmail(booking.getUser().getEmail());
            bookingDTO.setPassengerPhone(booking.getUser().getPhoneNumber());
            bookingDTOS.add(bookingDTO);
        }
        return bookingDTOS;
    }

    private boolean isFlightAvailable(Flight flight) {
        int bookedSeats = bookingRepository.countByFlight(flight);
        return flight.getCapacity() > bookedSeats;
    }

    private String generateBookingNumber() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        return "BN" + String.format("%06d", number);
    }

    private User createUser(BookingCreateDTO bookingDTO) {
        User newUser = new User();
        newUser.setName(bookingDTO.getPassengerName());
        newUser.setEmail(bookingDTO.getPassengerEmail());
        newUser.setPhoneNumber(bookingDTO.getPassengerPhone());
        newUser.setRole(Role.USER);
        return userRepository.save(newUser);
    }
}
