package com.retonttdata.user.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserRegistrationRequest {
    @NotBlank(message = "Full name is required")
    String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$",
            message = "Password must be at least 8 characters, contain one uppercase letter and one number")
    String password;

    String phone;
}