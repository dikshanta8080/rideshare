package com.acharya.dikshanta.rideshare.user.dto.request;

import com.acharya.dikshanta.rideshare.common.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record PassengerRegistrationRequest(

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^\\+?[1-9]\\d{7,14}$",
                message = "Invalid phone number"
        )
        String phoneNumber,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
        String password,

        @NotBlank(message = "Age is required")
        @Pattern(
                regexp = "^(1[89]|[2-9]\\d|1[0-1]\\d|120)$",
                message = "Age must be between 18 and 120"
        )
        String age,

        @NotNull(message = "Gender is required")
        Gender gender
) {
}
