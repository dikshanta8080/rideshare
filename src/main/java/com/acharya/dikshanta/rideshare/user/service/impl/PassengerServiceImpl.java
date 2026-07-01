package com.acharya.dikshanta.rideshare.user.service.impl;

import com.acharya.dikshanta.rideshare.common.dto.response.PagedResponse;
import com.acharya.dikshanta.rideshare.common.exceptions.BusinessException;
import com.acharya.dikshanta.rideshare.common.exceptions.ResourceNotFoundException;
import com.acharya.dikshanta.rideshare.user.dto.request.CreatePassengerRequest;
import com.acharya.dikshanta.rideshare.user.dto.request.UserFilterRequest;
import com.acharya.dikshanta.rideshare.user.dto.request.UserUpdateRequest;
import com.acharya.dikshanta.rideshare.user.dto.response.PassengerResponse;
import com.acharya.dikshanta.rideshare.user.mapper.PassengerMapper;
import com.acharya.dikshanta.rideshare.user.mapper.UserMapper;
import com.acharya.dikshanta.rideshare.user.model.PassengerProfile;
import com.acharya.dikshanta.rideshare.user.model.User;
import com.acharya.dikshanta.rideshare.user.repository.PassengerProfileRepository;
import com.acharya.dikshanta.rideshare.user.repository.UserRepository;
import com.acharya.dikshanta.rideshare.user.service.PassengerService;
import com.acharya.dikshanta.rideshare.user.specifications.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PassengerServiceImpl implements PassengerService {
    private final UserSpecification userSpecification;
    private final PasswordEncoder passwordEncoder;
    private final PassengerProfileRepository passengerProfileRepository;
    private final UserRepository userRepository;
    private final PassengerMapper passengerMapper;
    private final UserMapper userMapper;


    @Override
    @Transactional
    public PassengerResponse registerPassenger(CreatePassengerRequest request) {
        validateUniqueEmailAndNumber(request);
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = userMapper.toUserEntity(request, hashedPassword);
        PassengerProfile passengerProfile = createPassengerProfile(userRepository.save(user));
        PassengerProfile savedProfile = passengerProfileRepository.save(passengerProfile);
        return passengerMapper.toResponse(savedProfile);
    }


    @Override
    @Transactional(readOnly = true)
    public PagedResponse<PassengerResponse> getAllUsers(Pageable pageable, UserFilterRequest request) {
        Specification<User> userSpecification = UserSpecification.filterUsers(request);
        Page<PassengerResponse> passengers = userRepository.findAll(userSpecification, pageable)
                .map(user -> passengerMapper.toResponse(user.getPassengerProfile()));

        return PagedResponse.toPagedResponse(passengers);

    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        int result = userRepository.softDeleteUser(userId);
        if (result == 0) {
            throw new BusinessException("User not found");
        }
    }

    @Override
    public PassengerResponse updateUser(UserUpdateRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new BusinessException("User not found"));
        updateDetails(request, user);
        User saved = userRepository.save(user);
        PassengerProfile passengerProfile = getPassengerProfile(saved.getPassengerProfile().getId());

        passengerProfile.setUser(user);
        return passengerMapper.toResponse(passengerProfile);
    }

    private PassengerProfile getPassengerProfile(Long id) {
        return passengerProfileRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Passenger Not found"));
    }

    private void updateDetails(UserUpdateRequest request, User user) {
        if (request.name() != null) {
            user.setName(request.name());
        }
        if (request.age() != null) {
            user.setAge(request.age());
        }
        if (request.gender() != null) {
            user.setGender(request.gender());
        }
        if (request.email() != null) {
            user.setEmail(request.email());
        }
        if (request.phoneNumber() != null) {
            user.setPhoneNumber(request.phoneNumber());
        }
    }

    private void validateUniqueEmailAndNumber(CreatePassengerRequest request) {
        if (userRepository.existsByEmail((request.getEmail()))) {
            throw new BusinessException("Email already exists");
        }
        if (userRepository.existsByPhoneNumber(request.getPhoneNumbe())) {
            throw new BusinessException("Phone number already exists");
        }
    }

    private PassengerProfile createPassengerProfile(User user) {
        return PassengerProfile.builder()
                .user(user)
                .build();
    }
}
