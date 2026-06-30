CREATE TABLE vehicles
(
    id             BIGSERIAL PRIMARY KEY,
    created_at     TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted        BOOLEAN      NOT NULL DEFAULT FALSE,

    vehicle_name   VARCHAR(255) NOT NULL,
    vehicle_number VARCHAR(100) NOT NULL,
    vehicle_type   VARCHAR(20)  NOT NULL,
    vehicle_image  VARCHAR(500) NOT NULL,
    vehicle_color  VARCHAR(100) NOT NULL,
    vehicle_model  VARCHAR(255) NOT NULL,
    vehicle_year   VARCHAR(10)  NOT NULL,
    vehicle_owner  VARCHAR(255) NOT NULL,

    CONSTRAINT uk_vehicle_number UNIQUE (vehicle_number)
);

CREATE INDEX idx_vehicles_vehicle_number ON vehicles (vehicle_number);
CREATE INDEX idx_vehicles_deleted ON vehicles (deleted);


CREATE TABLE rider_profiles
(
    id                 BIGSERIAL PRIMARY KEY,
    created_at         TIMESTAMPTZ      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMPTZ      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted            BOOLEAN          NOT NULL DEFAULT FALSE,

    rider_status       VARCHAR(20)      NOT NULL,
    citizenship_number VARCHAR(100)     NOT NULL,
    license_number     VARCHAR(100)     NOT NULL,
    license_expiry     DATE             NOT NULL,
    total_trips        INTEGER          NOT NULL DEFAULT 0,
    total_earnings     INTEGER          NOT NULL DEFAULT 0,
    total_ratings      INTEGER          NOT NULL DEFAULT 0,
    average_rating     DOUBLE PRECISION NOT NULL DEFAULT 0.0,

    vehicle_id         BIGINT REFERENCES vehicles (id),
    user_id            BIGINT           NOT NULL REFERENCES users (id),

    CONSTRAINT uk_rider_profiles_user_id UNIQUE (user_id),
    CONSTRAINT uk_rider_profiles_vehicle_id UNIQUE (vehicle_id)
);

CREATE INDEX idx_rider_profiles_user_id ON rider_profiles (user_id);
CREATE INDEX idx_rider_profiles_vehicle_id ON rider_profiles (vehicle_id);
CREATE INDEX idx_rider_profiles_status ON rider_profiles (rider_status);
CREATE INDEX idx_rider_profiles_deleted ON rider_profiles (deleted);


CREATE TABLE passenger_profiles
(
    id         BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted    BOOLEAN     NOT NULL DEFAULT FALSE,

    user_id    BIGINT      NOT NULL REFERENCES users (id),

    CONSTRAINT uk_passenger_profiles_user_id UNIQUE (user_id)
);

CREATE INDEX idx_passenger_profiles_user_id ON passenger_profiles (user_id);
CREATE INDEX idx_passenger_profiles_deleted ON passenger_profiles (deleted);

