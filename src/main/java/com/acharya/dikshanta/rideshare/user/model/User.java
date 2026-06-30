package com.acharya.dikshanta.rideshare.user.model;

import com.acharya.dikshanta.rideshare.common.enums.Gender;
import com.acharya.dikshanta.rideshare.common.enums.Role;
import com.acharya.dikshanta.rideshare.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phone_number")
})
public class User extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "age", nullable = false)
    private String age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToOne(mappedBy = "user")
    private RiderProfile riderProfile;
    
    @OneToOne(mappedBy = "user")
    private PassengerProfile passengerProfile;
}
