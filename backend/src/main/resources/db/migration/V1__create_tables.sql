SET search_path TO public;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'factor_type_enum') THEN
        CREATE TYPE factor_type_enum AS ENUM (
            'HYDRO_SANITARY_SYSTEM',
            'RAINWATER_DRAINAGE_SYSTEM',
            'PAVING',
            'ELECTRICAL_NETWORK',
            'EARTHWORKS'
        );
    END IF;
END$$;

CREATE TABLE factor_types (
    id SERIAL PRIMARY KEY,
    factor_type factor_type_enum UNIQUE NOT NULL
);

CREATE TABLE price_factors (
    id SERIAL PRIMARY KEY,
    factor NUMERIC UNIQUE NOT NULL
);

CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    street VARCHAR(255),
    number VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    neighborhood VARCHAR(255),
    cep VARCHAR(20),
    price_factor_id INT,
    CONSTRAINT fk_address_price_factor FOREIGN KEY (price_factor_id) REFERENCES price_factors(id)
);

CREATE TABLE third_parties (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(20) UNIQUE NOT NULL,
    factor_type_id INT NOT NULL,
    CONSTRAINT fk_factor_type FOREIGN KEY (factor_type_id) REFERENCES factor_types(id)
);

CREATE TABLE tract_owners (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE tracts (
    id SERIAL PRIMARY KEY,
    square_meters NUMERIC NOT NULL,
    address_id INT,
    tract_owner_id INT,
    CONSTRAINT fk_tract_address FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE CASCADE,
    CONSTRAINT fk_tract_owner FOREIGN KEY (tract_owner_id) REFERENCES tract_owners(id)
);

CREATE TABLE quotes (
    id SERIAL PRIMARY KEY,
    tract_id INT,
    total_factors_price NUMERIC NOT NULL,
    create_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_quotes_tract FOREIGN KEY (tract_id) REFERENCES tracts(id) ON DELETE SET NULL
);

CREATE TABLE factors (
    id SERIAL PRIMARY KEY,
    quote_id INT NOT NULL,
    third_party_id INT NOT NULL,
    material_cost NUMERIC NOT NULL,
    labor_cost NUMERIC NOT NULL,
    factor_type_id INT NOT NULL,
    CONSTRAINT fk_factors_third_party FOREIGN KEY (third_party_id) REFERENCES third_parties(id),
    CONSTRAINT fk_factors_quote FOREIGN KEY (quote_id) REFERENCES quotes(id),
    CONSTRAINT fk_factor_type FOREIGN KEY (factor_type_id) REFERENCES factor_types(id)
);
