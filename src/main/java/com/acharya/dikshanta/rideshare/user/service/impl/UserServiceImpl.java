package com.acharya.dikshanta.rideshare.user.service.impl;

import com.acharya.dikshanta.rideshare.common.exceptions.BusinessException;
import com.acharya.dikshanta.rideshare.user.dto.request.PassengerRegistrationRequest;
import com.acharya.dikshanta.rideshare.user.dto.response.UserResponse;
import com.acharya.dikshanta.rideshare.user.mapper.UserMapper;
import com.acharya.dikshanta.rideshare.user.model.PassengerProfile;
import com.acharya.dikshanta.rideshare.user.model.User;
import com.acharya.dikshanta.rideshare.user.repository.PassengerProfileRepository;
import com.acharya.dikshanta.rideshare.user.repository.UserRepository;
import com.acharya.dikshanta.rideshare.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final PassengerProfileRepository passengerProfileRepository;
    private final UserRepository userRepository;

    private PassengerProfile getPassengerProfile(User user) {
        return PassengerProfile.builder()
                .user(user)
                .build();
    }

    @Override
    @Transactional
    public UserResponse registerPassenger(PassengerRegistrationRequest request) {
        checkIfAlreadyExists(request);
        String hashedPassword = passwordEncoder.encode(request.password());
        User user = UserMapper.toUser(request, hashedPassword);
        PassengerProfile passengerProfile = getPassengerProfile(userRepository.save(user));
        PassengerProfile savedProfile = passengerProfileRepository.save(passengerProfile);
        return UserMapper.toResponse(savedProfile);
    }

    private void checkIfAlreadyExists(PassengerRegistrationRequest request) {
        if (userRepository.existsByEmail((request.email()))) {
            throw new BusinessException("Email already exists");
        }
        if (userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new BusinessException("Phone number already exists");
        }
    }
}
