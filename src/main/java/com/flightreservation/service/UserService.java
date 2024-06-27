package com.flightreservation.service;

import com.flightreservation.dto.UserLoginDTO;
import com.flightreservation.dto.UserRegistrationDTO;
import com.flightreservation.dto.converter.UserRegistrationConverter;
import com.flightreservation.exception.AuthException;
import com.flightreservation.exception.BusinessRuleException;
import com.flightreservation.model.entity.User;
import com.flightreservation.model.enums.Role;
import com.flightreservation.repository.UserRepository;
import com.flightreservation.security.JwtTokenUtil;
import com.flightreservation.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Lazy
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRegistrationConverter userRegistrationConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String login(UserLoginDTO userLoginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
            );
            return jwtTokenUtil.generateJwtToken(authentication);
        } catch (AuthenticationException e) {
            throw new AuthException("Invalid email/password supplied");
        }
    }

    public User registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            throw new BusinessRuleException("Bu e-posta adresi zaten kayıtlı.");
        }
        User user = userRegistrationConverter.convertToUser(userRegistrationDTO);
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("User id ile kullanıcı bulunamadı."));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        User user = userOptional.get();
        return new JwtUserDetails(user.getId(), user.getEmail(), user.getPassword(), user.getName(),
                user.getPhoneNumber(), user.getRole());
    }

}
