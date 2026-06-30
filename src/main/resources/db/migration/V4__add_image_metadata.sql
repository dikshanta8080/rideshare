CREATE TABLE image_metadata
(
    id         BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted    BOOLEAN      NOT NULL DEFAULT FALSE,

    file_name  VARCHAR(255) NOT NULL,
    file_path  VARCHAR(500) NOT NULL UNIQUE,
    file_type  VARCHAR(100) NOT NULL,
    file_size  BIGINT       NOT NULL
);

CREATE INDEX idx_image_metadata_file_path ON image_metadata (file_path);
CREATE INDEX idx_image_metadata_deleted ON image_metadata (deleted);


ALTER TABLE users
    ADD COLUMN image_metadata_id BIGINT REFERENCES image_metadata (id);

CREATE INDEX idx_users_image_metadata_id ON users (image_metadata_id);

ALTER TABLE vehicles
    ADD COLUMN image_metadata_id BIGINT REFERENCES image_metadata (id);

CREATE INDEX idx_vehicles_image_metadata_id ON vehicles (image_metadata_id);
