package com.acharya.dikshanta.rideshare.user.dto.request;

public record UserFilterRequest(
        String name,
        String gender,
        Integer minAge,
        Integer maxAge,
        String role
) {
}
