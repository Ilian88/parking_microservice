CREATE TABLE IF NOT EXISTS purged_vehicle (
    id BIGSERIAL PRIMARY KEY,
    license_plate VARCHAR(50) NOT NULL,
    vehicle_type VARCHAR(50),
    euro_category VARCHAR(20),
    time_spent BIGINT NOT NULL,
    amount_payed NUMERIC(12,2),
    currency VARCHAR(10)
);
