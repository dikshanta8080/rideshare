package com.acharya.dikshanta.rideshare.user.mapper;

import com.acharya.dikshanta.rideshare.user.dto.response.PassengerResponse;
import com.acharya.dikshanta.rideshare.user.model.PassengerProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PassengerMapper {
    private final UserMapper userMapper;

    public PassengerResponse toResponse(PassengerProfile passengerProfile) {
        return PassengerResponse.builder().userResponse(
                userMapper.toUserResponse(passengerProfile.getUser())
        ).passengerId(passengerProfile.getId()).build();
    }
}
