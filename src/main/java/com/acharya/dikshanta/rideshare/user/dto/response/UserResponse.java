package com.acharya.dikshanta.rideshare.user.dto.response;

import com.acharya.dikshanta.rideshare.common.enums.Gender;
import com.acharya.dikshanta.rideshare.common.enums.Role;
import lombok.Builder;

@Builder
public record UserResponse(
        Long userId,
        Long passengerProfileId,
        String name,
        String email,
        String phoneNumber,
        String age,
        Gender gender,
        Role role,
        String imageUrl
) {
}
