
ALTER TABLE rider_profiles
    ALTER COLUMN total_earnings TYPE NUMERIC(19, 2) USING total_earnings::NUMERIC;
