package io.reflectoring.SpringBoot3.controllers;

import io.reflectoring.SpringBoot3.DTO.AuthUserDTO;
import io.reflectoring.SpringBoot3.DTO.ForgotPasswordDTO;
import io.reflectoring.SpringBoot3.DTO.LoginDTO;
import io.reflectoring.SpringBoot3.DTO.ResetPasswordDTO;
import io.reflectoring.SpringBoot3.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for User Authentication")
public class AuthUserController {

    private final AuthenticationService authenticationService;

    // Register a new user
    @PostMapping("/register")
    @Operation(summary = "Registers a new user")
    public ResponseEntity<String> register(@Valid @RequestBody AuthUserDTO authUserDTO) {
        return ResponseEntity.ok(authenticationService.register(authUserDTO));
    }

    // User login
    @PostMapping("/login")
    @Operation(summary = "Authenticates user login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authenticationService.login(loginDTO));
    }
//UC13
    // Forgot Password
    @PutMapping("/forgotPassword/{email}")
    @Operation(summary = "Allows users to reset their password when they forget it")
    public ResponseEntity<String> forgotPassword(@PathVariable String email, @RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        return ResponseEntity.ok(authenticationService.forgotPassword(email, forgotPasswordDTO.getPassword()));
    }

    // Reset Password (User must be logged in)
    @PutMapping("/resetPassword/{email}")
    @Operation(summary = "Allows logged-in users to change their password")
    public ResponseEntity<String> resetPassword(
            @PathVariable String email,
            @RequestBody ResetPasswordDTO resetPasswordDTO) {
        return ResponseEntity.ok(authenticationService.resetPassword(email, resetPasswordDTO.getCurrentPassword(), resetPasswordDTO.getNewPassword()));
    }
}
