package com.acharya.dikshanta.rideshare.user.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;

@Builder
public record PassengerResponse(
        @JsonUnwrapped UserResponse userResponse,
        Long passengerId
) {
}
