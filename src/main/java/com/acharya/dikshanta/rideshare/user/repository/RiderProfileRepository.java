package com.acharya.dikshanta.rideshare.user.repository;

import com.acharya.dikshanta.rideshare.user.model.RiderProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderProfileRepository extends JpaRepository<RiderProfile, Long>, JpaSpecificationExecutor<RiderProfile> {
}
