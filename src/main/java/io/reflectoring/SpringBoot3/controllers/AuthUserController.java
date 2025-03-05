package io.reflectoring.SpringBoot3.controllers;

import io.reflectoring.SpringBoot3.DTO.AuthUserDTO;
import io.reflectoring.SpringBoot3.DTO.LoginDTO;
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
    //UC9 registration
    @PostMapping("/register")
    @Operation(summary = "Registers a new user")
    public ResponseEntity<String> register(@Valid @RequestBody AuthUserDTO authUserDTO) {
        return ResponseEntity.ok(authenticationService.register(authUserDTO));
    }

    //UC10 login
    @PostMapping("/login")
    @Operation(summary = "Authenticates user login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authenticationService.login(loginDTO));
    }
}
