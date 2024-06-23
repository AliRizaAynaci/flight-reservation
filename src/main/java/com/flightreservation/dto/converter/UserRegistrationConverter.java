package com.flightreservation.dto.converter;

import com.flightreservation.dto.UserRegistrationDTO;
import com.flightreservation.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationConverter {

    private final ModelMapper modelMapper;

    public UserRegistrationConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User convertToUser(UserRegistrationDTO userRegistrationDTO) {
       return modelMapper.map(userRegistrationDTO, User.class);
    }
}