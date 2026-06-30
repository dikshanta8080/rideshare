package com.acharya.dikshanta.rideshare.user.model;

import com.acharya.dikshanta.rideshare.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "passenger_profiles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PassengerProfile extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
