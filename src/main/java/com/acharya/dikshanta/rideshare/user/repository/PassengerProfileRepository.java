package com.acharya.dikshanta.rideshare.user.repository;

import com.acharya.dikshanta.rideshare.user.model.PassengerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerProfileRepository extends JpaRepository<PassengerProfile, Long> {
    boolean existsByUser_Email(String email);

    boolean existsByUser_PhoneNumber(String phoneNumber);
}
