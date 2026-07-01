package com.acharya.dikshanta.rideshare.user.service;

import com.acharya.dikshanta.rideshare.common.dto.response.PagedResponse;
import com.acharya.dikshanta.rideshare.user.dto.request.CreatePassengerRequest;
import com.acharya.dikshanta.rideshare.user.dto.request.UserFilterRequest;
import com.acharya.dikshanta.rideshare.user.dto.request.UserUpdateRequest;
import com.acharya.dikshanta.rideshare.user.dto.response.PassengerResponse;
import org.springframework.data.domain.Pageable;

public interface PassengerService {
    PassengerResponse registerPassenger(CreatePassengerRequest request);

    PagedResponse<PassengerResponse> getAllUsers(Pageable pageable, UserFilterRequest request);

    void deleteUser(Long userId);

    PassengerResponse updateUser(UserUpdateRequest request, Long userId);
}
