package com.acharya.dikshanta.rideshare.user.mapper;

import com.acharya.dikshanta.rideshare.common.enums.Role;
import com.acharya.dikshanta.rideshare.user.dto.request.PassengerRegistrationRequest;
import com.acharya.dikshanta.rideshare.user.dto.response.UserResponse;
import com.acharya.dikshanta.rideshare.user.model.PassengerProfile;
import com.acharya.dikshanta.rideshare.user.model.User;

public class UserMapper {
    public static User toUser(PassengerRegistrationRequest request, String hashedPassword) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .password(hashedPassword)
                .age(request.age())
                .gender(request.gender())
                .role(Role.PASSENGER)
                .build();
    }

    public static UserResponse toResponse(PassengerProfile passengerProfile) {
        return UserResponse.builder()
                .userId(passengerProfile.getUser().getId())
                .passengerProfileId(passengerProfile.getId())
                .name(passengerProfile.getUser().getName())
                .email(passengerProfile.getUser().getEmail())
                .phoneNumber(passengerProfile.getUser().getPhoneNumber())
                .age(passengerProfile.getUser().getAge())
                .gender(passengerProfile.getUser().getGender())
                .role(passengerProfile.getUser().getRole())
                .imageUrl(null)
                .build();
    }
}
