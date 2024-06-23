package com.flightreservation.dto.converter;

import com.flightreservation.dto.FlightDTO;
import com.flightreservation.model.entity.Flight;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FlightConverter {

    private final ModelMapper modelMapper;

    public FlightConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FlightDTO convertToDto(Flight flight) {
        return modelMapper.map(flight, FlightDTO.class);
    }
}
