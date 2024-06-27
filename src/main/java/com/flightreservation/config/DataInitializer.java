package com.flightreservation.config;

import com.flightreservation.model.entity.User;
import com.flightreservation.model.enums.Role;
import com.flightreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initAdminUser() {
        return args -> {
            User adminUser = new User();
            adminUser.setName("Ali");
            adminUser.setEmail("ali@gmail.com");
            adminUser.setPhoneNumber("123456789");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setRole(Role.ADMIN);

            userRepository.save(adminUser);

            System.out.println("Admin kullanıcı oluşturuldu.");
        };
    }
}
