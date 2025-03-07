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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    /**
     * Registers a new user
     */
    @Transactional
    public String register(@Valid AuthUserDTO authUserDTO) {
        // Check if email already exists
        if (authUserRepository.existsByEmail(authUserDTO.getEmail())) {
            return "Email already registered!";
        }

        // Create new user
        AuthUser newUser = new AuthUser();
        newUser.setFirstName(authUserDTO.getFirstName());
        newUser.setLastName(authUserDTO.getLastName());
        newUser.setEmail(authUserDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(authUserDTO.getPassword())); // Encrypt password

        // Save user in DB
        authUserRepository.save(newUser);
        System.out.println("User saved: " + newUser.getEmail());

        // Generate JWT Token
        String token = jwtService.generateToken(newUser.getEmail());

        // Send Confirmation Email
        emailService.sendEmail(newUser.getEmail(), "Registration Successful", "Your account has been created!");

        return "User registered successfully. Token: " + token;
    }

    /**
     * Handles user login and returns JWT token
     */
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

    /**
     * Forgot Password - Updates user's password
     */
    @Transactional
    public String forgotPassword(String email, String newPassword) {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Sorry! We cannot find the user email: " + email);
        }

        AuthUser user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        authUserRepository.save(user);

        emailService.sendEmail(user.getEmail(), "Password Changed", "Your password has been updated successfully.");

        return "Password has been changed successfully!";
    }

    /**
     * Reset Password - Changes password when the user is logged in
     */
    @Transactional
    public String resetPassword(String email, String currentPassword, String newPassword) {
        AuthUser user = authUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password is incorrect!");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        authUserRepository.save(user);

        return "Password reset successfully!";
    }
}
