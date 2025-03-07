package io.reflectoring.SpringBoot3.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResetPasswordDTO {

    @NotNull(message = "Current password cannot be null")
    private String currentPassword;

    @NotNull(message = "New password cannot be null")
    private String newPassword;
}

