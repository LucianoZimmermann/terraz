-- Create tracts table
CREATE TABLE tracts (
    id SERIAL PRIMARY KEY,
    square_meters FLOAT NOT NULL
);

-- Create price_factors table
CREATE TABLE price_factors (
    id SERIAL PRIMARY KEY,
    factor FLOAT NOT NULL
);

-- Create addresses table
CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    street VARCHAR(255),
    city VARCHAR(255),
    neighborhood VARCHAR(255),
    cep VARCHAR(20),
    price_factor_id BIGINT NOT NULL,
    FOREIGN KEY (price_factor_id) REFERENCES price_factors(id)
);

-- Create tract_owners table
CREATE TABLE tract_owners (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    tract_id BIGINT NOT NULL,
    FOREIGN KEY (tract_id) REFERENCES tracts(id)
);

-- Create quotes table
CREATE TABLE quotes (
    id SERIAL PRIMARY KEY,
    tract_id BIGINT NOT NULL,
    tract_owner_id BIGINT NOT NULL,
    total_price FLOAT NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tract_id) REFERENCES tracts(id),
    FOREIGN KEY (tract_owner_id) REFERENCES tract_owners(id)
);
