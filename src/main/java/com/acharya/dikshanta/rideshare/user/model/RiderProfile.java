package com.acharya.dikshanta.rideshare.user.model;

import com.acharya.dikshanta.rideshare.common.enums.RiderStatus;
import com.acharya.dikshanta.rideshare.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rider_profiles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RiderProfile extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "rider_status", nullable = false)
    private RiderStatus riderStatus;

    @Column(name = "citizenship_number", nullable = false)
    private String citizenshipNumber;

    @Column(name = "license_number", nullable = false)
    private String licenseNumber;

    @Column(name = "license_expiry", nullable = false)
    private LocalDate licenseExpiry;

    @Column(name = "total_trips", nullable = false)
    private Integer totalTrips;

    @Column(name = "total_earnings", nullable = false)
    private BigDecimal totalEarnings;

    @Column(name = "total_ratings", nullable = false)
    private Integer totalRatings;

    @Column(name = "average_rating", nullable = false)
    private Double averageRating;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
