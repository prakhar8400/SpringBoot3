package io.reflectoring.SpringBoot3.services;

import io.reflectoring.SpringBoot3.DTO.AuthUserDTO;
import io.reflectoring.SpringBoot3.DTO.LoginDTO;
import io.reflectoring.SpringBoot3.Entity.AuthUser;
import io.reflectoring.SpringBoot3.Repository.AuthUserRepository;
import io.reflectoring.SpringBoot3.security.JwtService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    @Transactional
    public String register(@Valid AuthUserDTO authUserDTO) {
        if (authUserRepository.existsByEmail(authUserDTO.getEmail())) {
            return "Email already registered!";
        }

        AuthUser newUser = new AuthUser();
        newUser.setFirstName(authUserDTO.getFirstName());
        newUser.setLastName(authUserDTO.getLastName());
        newUser.setEmail(authUserDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(authUserDTO.getPassword())); // Encrypt password
        authUserRepository.save(newUser);

        // Generate JWT Token
        String token = jwtService.generateToken(newUser.getEmail());
//UC11
        // Send Email Confirmation
        emailService.sendEmail(newUser.getEmail(), "Registration Successful", "Your account has been created!");

        return "User registered successfully. Token: " + token;
    }

    public String login(@Valid LoginDTO loginDTO) {
        AuthUser user = authUserRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return "Invalid credentials";
        }

        // Generate JWT Token
        String token = jwtService.generateToken(user.getEmail());

        return "Login successful. Token: " + token;
    }
}
