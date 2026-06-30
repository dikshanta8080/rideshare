package com.acharya.dikshanta.rideshare.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "image_metadata")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ImageMetaData extends BaseEntity {

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, unique = true)
    private String filePath;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private Long fileSize;
}
