package io.reflectoring.SpringBoot3.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AuthUserDTO {

    @NotNull(message = "First name cannot be null")
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "First letter must be uppercase")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "First letter must be uppercase")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must have at least 1 uppercase letter, 1 special character, 1 number, and be at least 8 characters long"
    )
    private String password;
}
