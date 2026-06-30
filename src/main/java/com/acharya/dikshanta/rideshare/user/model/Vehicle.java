package com.acharya.dikshanta.rideshare.user.model;

import com.acharya.dikshanta.rideshare.common.enums.VehicleType;
import com.acharya.dikshanta.rideshare.common.model.BaseEntity;
import com.acharya.dikshanta.rideshare.common.model.ImageMetaData;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "vehicles",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_vehicle_number",
                        columnNames = "vehicle_number"
                )
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Vehicle extends BaseEntity {

    @Column(name = "vehicle_name", nullable = false)
    private String vehicleName;

    @Column(name = "vehicle_number", nullable = false)
    private String vehicleNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @Column(name = "vehicle_image", nullable = false)
    private String vehicleImage;

    @Column(name = "vehicle_color", nullable = false)
    private String vehicleColor;

    @Column(name = "vehicle_model", nullable = false)
    private String vehicleModel;

    @Column(name = "vehicle_year", nullable = false)
    private String vehicleYear;

    @Column(name = "vehicle_owner", nullable = false)
    private String vehicleOwner;

    @OneToOne(mappedBy = "vehicle")
    private RiderProfile riderProfile;

    @OneToOne
    @JoinColumn(name = "image_metadata_id")
    private ImageMetaData imageMetaData;
}