package com.flightreservation.dto.converter;

import com.flightreservation.dto.BookingCreateDTO;
import com.flightreservation.dto.BookingDTO;
import com.flightreservation.model.entity.Booking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookingConverter {

    private final ModelMapper modelMapper;

    public BookingConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookingDTO convertToDto(Booking booking) {
        return modelMapper.map(booking, BookingDTO.class);
    }

    public Booking convertToEntity(BookingCreateDTO bookingDTO) {
        return modelMapper.map(bookingDTO, Booking.class);
    }
}
