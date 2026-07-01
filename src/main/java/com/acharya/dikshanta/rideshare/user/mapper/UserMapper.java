package com.acharya.dikshanta.rideshare.user.mapper;

import com.acharya.dikshanta.rideshare.common.enums.Role;
import com.acharya.dikshanta.rideshare.user.dto.request.UserRequest;
import com.acharya.dikshanta.rideshare.user.dto.response.UserResponse;
import com.acharya.dikshanta.rideshare.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public <T extends UserRequest> User toUserEntity(T request, String hashedPassword) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumbe())
                .password(hashedPassword)
                .age(request.getAge())
                .gender(request.getGender())
                .role(Role.PASSENGER)
                .build();
    }


    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .age(user.getAge())
                .gender(user.getGender())
                .role(user.getRole())
                .imageUrl(user.getImageMetaData().getFilePath())
                .build();
    }

}
