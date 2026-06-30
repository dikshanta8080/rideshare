package com.acharya.dikshanta.rideshare.user.service;

import com.acharya.dikshanta.rideshare.user.dto.request.PassengerRegistrationRequest;
import com.acharya.dikshanta.rideshare.user.dto.response.UserResponse;

public interface UserService {
    UserResponse registerPassenger(PassengerRegistrationRequest request);
}
