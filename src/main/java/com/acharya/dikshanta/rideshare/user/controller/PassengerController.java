package com.acharya.dikshanta.rideshare.user.controller;

import com.acharya.dikshanta.rideshare.common.dto.request.PaginationRequest;
import com.acharya.dikshanta.rideshare.common.dto.response.ApiResponse;
import com.acharya.dikshanta.rideshare.common.dto.response.PagedResponse;
import com.acharya.dikshanta.rideshare.user.dto.request.CreatePassengerRequest;
import com.acharya.dikshanta.rideshare.user.dto.request.UserFilterRequest;
import com.acharya.dikshanta.rideshare.user.dto.response.PassengerResponse;
import com.acharya.dikshanta.rideshare.user.service.impl.PassengerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class PassengerController {
    private final PassengerServiceImpl passengerService;

    @PostMapping
    public ResponseEntity<ApiResponse<PassengerResponse>> registerUser(@Valid @RequestBody CreatePassengerRequest request) {
        var passenger = passengerService.registerPassenger(request);
        return ResponseEntity.ok(ApiResponse.success(passenger, "Passenger Registered Successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<PassengerResponse>>> getUsers(
            @ModelAttribute PaginationRequest paginationRequest,
            @ModelAttribute UserFilterRequest request
    ) {
        var passengers = passengerService.getAllUsers(paginationRequest.toPageable(), request);
        return ResponseEntity.ok(ApiResponse.success(passengers, "All users fetched successfully"));
    }
}
