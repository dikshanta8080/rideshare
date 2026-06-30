package com.acharya.dikshanta.rideshare.user.controller;

import com.acharya.dikshanta.rideshare.common.dto.response.ApiResponse;
import com.acharya.dikshanta.rideshare.user.dto.request.PassengerRegistrationRequest;
import com.acharya.dikshanta.rideshare.user.dto.response.UserResponse;
import com.acharya.dikshanta.rideshare.user.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(@Valid @RequestBody PassengerRegistrationRequest request) {
        UserResponse userResponse = userService.registerPassenger(request);
        return ResponseEntity.ok(ApiResponse.success(userResponse, "Passenger Registered Successfully"));
    }
}
