package com.acharya.dikshanta.rideshare.user.dto.request;

import com.acharya.dikshanta.rideshare.common.enums.Gender;

public record UserUpdateRequest(
        String name,
        String email,
        String phoneNumber,
        Integer age,
        Gender gender
) {
}
